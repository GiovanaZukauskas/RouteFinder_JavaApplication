package school.sptech;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import school.sptech.entidades.Direction;
import school.sptech.entidades.Passage;
import school.sptech.entidades.Segment;
import school.sptech.entidades.TimeStamp;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeitorDados {
    private final JdbcTemplate jdbcTemplate;
    private final S3Client s3Client;

    public LeitorDados(JdbcTemplate jdbcTemplate, S3Client s3Client) {
        this.jdbcTemplate = jdbcTemplate;
        this.s3Client = s3Client;
    }

    public void processar(String bucket, String key) {
        System.out.println("Iniciando processamento do arquivo: " + key);

        try (InputStream inputStream = s3Client.getObject(GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build())) {

            if (key.endsWith(".xls")) {
                throw new UnsupportedOperationException("Arquivos .xls não são suportados no modo SAX.");
            }
            IOUtils.setByteArrayMaxOverride(1_000_000_000);
            OPCPackage pkg = OPCPackage.open(inputStream);
            XSSFReader reader = new XSSFReader(pkg);
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(pkg);
            DataFormatter formatter = new DataFormatter();

            final int BATCH_SIZE = 30;
            List<TimeStamp> batchTimeStamp = new ArrayList<>(BATCH_SIZE);

            // Carrega todas as áreas existentes no banco e as coloca em um Map
            Map<String, Integer> passagensCadastradas = new HashMap<>();
            jdbcTemplate.query("SELECT id_passage, name_passage FROM passage", rs -> {
                passagensCadastradas.put(rs.getString("name_passage").trim().toLowerCase(), rs.getInt("id_passage"));
            });

            Map<String, Integer> direcaoCadastrada = new HashMap<>();
            jdbcTemplate.query("SELECT id_direction, name_direction FROM direction", rs -> {
                direcaoCadastrada.put(rs.getString("name_direction").trim().toLowerCase(), rs.getInt("id_direction"));
            });

            Map<String, Integer> segmentoCadastrado = new HashMap<>();
            jdbcTemplate.query("SELECT id_segment, name_segment FROM segment", rs -> {
                segmentoCadastrado.put(rs.getString("name_segment").trim().toLowerCase(), rs.getInt("id_segment"));
            });


            SheetContentsHandler handler = new SheetContentsHandler() {
                private Passage passage;
                private Direction direction;
                private Segment segment;
                private TimeStamp timeStamp;

                @Override
                public void startRow(int rowNum) {
                    if (rowNum == 0) {
                        passage = null;
                        direction = null;
                        segment = null;
                        timeStamp = null;
                        return;
                    }
                    passage = new Passage();
                    direction = new Direction();
                    segment = new Segment();
                    timeStamp = new TimeStamp();
                }

                @Override
                public void endRow(int rowNum) {
                    if (passage != null && direction != null && segment != null && timeStamp != null) {
                        String nomePassage = passage.getName().trim().toLowerCase();
                        String nomeDirection = direction.getName().trim().toLowerCase();
                        String nomeSegment = segment.getNome().trim().toLowerCase();

                        if (!passagensCadastradas.containsKey(nomePassage)) {
                            jdbcTemplate.update("INSERT INTO passage (name_passage, region, type) VALUES (?, ?, ?)", passage.getName(), passage.getRegion(), passage.getType());
                            Integer idPassage = jdbcTemplate.queryForObject("SELECT id_passage FROM passage WHERE name_passage = ?", Integer.class, passage.getName());
                            passagensCadastradas.put(nomePassage, idPassage);
                        }
                        if (!direcaoCadastrada.containsKey(nomeDirection)) {
                            jdbcTemplate.update("INSERT INTO direction (name_direction, fk_passage) VALUES (?, ?)", direction.getName(), passagensCadastradas.get(nomePassage));
                            Integer idDirection = jdbcTemplate.queryForObject("SELECT id_direction FROM direction WHERE name_direction = ?", Integer.class, direction.getName());
                            direcaoCadastrada.put(nomeDirection, idDirection);
                        }
                        if (!segmentoCadastrado.containsKey(nomeSegment)) {
                            jdbcTemplate.update("INSERT INTO segment (name_segment, fk_direction) VALUES (?, ?)", segment.getNome(), direcaoCadastrada.get(nomeDirection));
                            Integer idSegment = jdbcTemplate.queryForObject("SELECT id_segment FROM segment WHERE name_segment = ?", Integer.class, segment.getNome());
                            segmentoCadastrado.put(nomeSegment, idSegment);
                            timeStamp.setFkSegment(idSegment);
                        } else {
                            timeStamp.setFkSegment(segmentoCadastrado.get(nomeSegment));
                        }
                        batchTimeStamp.add(timeStamp);
                        if (batchTimeStamp.size() == BATCH_SIZE) {
                            enviarBatch(batchTimeStamp);
                            batchTimeStamp.clear();
                        }
                    }
                }

                @Override
                public void cell(String cellReference, String formattedValue, XSSFComment comment) {
                    if (passage == null && direction == null && segment == null && timeStamp == null) return;
                    String col = cellReference.replaceAll("\\d", "");
                    int currentCol = colunaParaIndice(col);

                    formattedValue = formattedValue.trim();

                    switch (currentCol) {
                        case 0 -> passage.setName(tratarTexto(formattedValue));
                        case 1 -> direction.setName(tratarTexto(formattedValue));
                        case 2 -> passage.setType(tratarTexto(formattedValue));
                        case 3 -> passage.setRegion(tratarTexto(formattedValue));
                        case 4 -> {
                            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                                    .appendPattern("d/M/")
                                    .appendValueReduced(ChronoField.YEAR, 2, 4, 2000)
                                    .appendPattern(" ")
                                    .appendValue(ChronoField.HOUR_OF_DAY, 1, 2, SignStyle.NORMAL)
                                    .appendLiteral(':')
                                    .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
                                    .toFormatter();
                            LocalDateTime dateTime = LocalDateTime.parse(formattedValue, formatter);
                            timeStamp.setDataHorario(dateTime);
                        }
                        case 5 -> timeStamp.setJamSize(parseInt(formattedValue));
                        case 6 -> segment.setNome(tratarTexto(formattedValue));
                    }
                }

            };

            XMLReader parser = XMLReaderFactory.createXMLReader();
            XSSFSheetXMLHandler xmlHandler = new XSSFSheetXMLHandler(
                    reader.getStylesTable(), null, strings, handler, formatter, false);
            parser.setContentHandler(xmlHandler);

            try (InputStream sheet = reader.getSheetsData().next()) {
                InputSource sheetSource = new InputSource(sheet);
                parser.parse(sheetSource);
            }

            if (!batchTimeStamp.isEmpty()) {
                enviarBatch(batchTimeStamp);
                batchTimeStamp.clear();
            }

            System.out.println("✔ Leitura da planilha '" + key + "' finalizada.");
        } catch (Exception e) {
            System.err.println("Erro ao processar a planilha '" + key + "': " + e.getMessage());
        }
    }

    private int parseInt(String value) {
        return value != null && !value.isEmpty() ? (int) Integer.parseInt(value) : 0;
    }

    private String tratarTexto(String valor) {
        return valor != null ? valor.trim().toUpperCase() : "";
    }


    private int colunaParaIndice(String col) {
        int index = 0;
        for (char c : col.toCharArray()) {
            index = index * 26 + (c - 'A' + 1);
        }
        return index - 1;
    }

    private void enviarBatch(List<TimeStamp> horarioPicoList) {
        System.out.println("Inserindo " + horarioPicoList.size() + " registros no banco.");

        String sqlHorarioPico = "INSERT INTO timestamp (data_horario, jam_size, fk_segment) VALUES (?, ?, ?)";

        try {
            jdbcTemplate.batchUpdate(sqlHorarioPico, horarioPicoList, horarioPicoList.size(), (ps, horario) -> {
                ps.setTimestamp(1, Timestamp.valueOf(horario.getDataHorario()));
                ps.setInt(2, horario.getJamSize());
                ps.setInt(3, horario.getFkSegment());
            });
        } catch (Exception e) {
            System.err.println("Erro ao inserir batch: " + e.getMessage());
        }
    }
}
