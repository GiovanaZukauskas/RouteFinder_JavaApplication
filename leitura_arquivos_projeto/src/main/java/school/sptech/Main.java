package school.sptech;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.springframework.jdbc.core.JdbcTemplate;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//public class Main {
//
//    public static void main(String[] args) throws IOException, OpenXML4JException, SAXException {
//
//        ConexaoBanco conexao = new ConexaoBanco();
//        JdbcTemplate jdbcTemplate = conexao.getJdbcTemplate();
//
//        IOUtils.setByteArrayMaxOverride(1_000_000_000);
//        OPCPackage arquivo = OPCPackage.open("C:/Users/user/Downloads/planilha_dados.xlsx");
//        XSSFReader leitor = new XSSFReader(arquivo);
//        ReadOnlySharedStringsTable texto = new ReadOnlySharedStringsTable(arquivo);
//        InputStream folha = leitor.getSheetsData().next();
//
//        SheetContentsHandler manipulador = new SheetContentsHandler() {
//            List<String> valoresLinha;
//            DadosTransito dadosTransito;
//
//            // Método que será executado a cada início de linha
//            @Override
//            public void startRow(int rowNum) {
//                dadosTransito = new DadosTransito();
//                valoresLinha = new ArrayList<>();
//            }
//
//            Integer colunaAtual = -1;
//
//            // Método que será executado a cada célula lida
//            @Override
//            public void cell(String cellReference, String formattedValue, XSSFComment comment) {
//                // Verifica a coluna atual para saber se foi pulada alguma célula vazia
//                int coluna = converterLetraParaIndice(cellReference.replaceAll("\\d", ""));
//
//                /*
//                    Se a coluna da célula for diferente da coluna que era para ser a atual, serão
//                    adicionados valores nulos para garantir a compatibilidade do vetor com a quantidade
//                    de atributos da classe
//                */
//                while (++colunaAtual < coluna) {
//                    valoresLinha.add(null);
//                }
//
//                valoresLinha.add(formattedValue.trim());
//                colunaAtual = coluna;
//            }
//
//            // Método que será executado no final de cada linha
//            @Override
//            public void endRow(int rowNum) {
//                if (rowNum == 0) return;
//                try {
//                    // Atribuindo os valores da lista aos atributos do objeto
//                    dadosTransito.setPassage(valoresLinha.get(0));
//                    dadosTransito.setDirection(valoresLinha.get(1));
//                    dadosTransito.setType(valoresLinha.get(2));
//                    dadosTransito.setRegion(valoresLinha.get(3));
//                    double excelDate = Double.parseDouble(valoresLinha.get(4));
//                    Date dataJava = DateUtil.getJavaDate(excelDate); // org.apache.poi.ss.usermodel.DateUtil
//                    Instant instant = dataJava.toInstant();
//                    dadosTransito.setTimestamp( instant.atZone(ZoneId.systemDefault()).toLocalDateTime());
//                    dadosTransito.setJamSize(Integer.parseInt(valoresLinha.get(5)));
//                    dadosTransito.setSegment(valoresLinha.get(6));
//                    jdbcTemplate.update("Insert into dados (passage, direction, type, region, dataRegistro, jamSize, segment) VALUES (?, ?, ?, ?, ?, ?, ?)", dadosTransito.getPassage(), dadosTransito.getDirection(), dadosTransito.getType(), dadosTransito.getRegion(), dadosTransito.getTimestamp(), dadosTransito.getJamSize(), dadosTransito.getSegment());
//                    System.out.println(dadosTransito);
//
//                } catch (Exception e) {
//                   System.out.printf("Erro ao inserir linha %d: %s", rowNum, e.getMessage());
//                }
//            }
//        };
//
//        XMLReader parser = XMLReaderFactory.createXMLReader();
//        XSSFSheetXMLHandler xmlHandler = new XSSFSheetXMLHandler(null, null, texto, manipulador, null, false);
//        parser.setContentHandler(xmlHandler);
//        parser.parse(new InputSource(folha));
//        folha.close();
//
//    }
//
//    private static Integer converterLetraParaIndice(String coluna) {
//        Integer indice = 0;
//        for (int i = 0; i < coluna.length(); i++) {
//            // Quantidade de colunas existente na tabela
//            indice *= 7;
//            indice += coluna.charAt(i) - 'A' + 1;
//        }
//        return indice - 1;
//    }
//
//}
