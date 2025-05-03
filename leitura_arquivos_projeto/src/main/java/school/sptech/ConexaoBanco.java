package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.entidades.Direction;
import school.sptech.entidades.Passage;
import school.sptech.entidades.Segment;
import school.sptech.entidades.TimeStamp;

import java.util.List;

public class ConexaoBanco {

    private final JdbcTemplate jdbcTemplate;
    private final BasicDataSource basicDataSource;

    public ConexaoBanco() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(ConfigLoader.get("IP"));
        basicDataSource.setUsername(ConfigLoader.get("USER"));
        basicDataSource.setPassword(ConfigLoader.get("SENHA"));

        this.basicDataSource = basicDataSource;
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public JdbcTemplate getJdbcTemplate() {

        return jdbcTemplate;
    }

    public void insertPassage(Passage passage) {

        List<Passage> passageValidacao = jdbcTemplate.query(
                "SELECT * FROM passage WHERE nome = ?",
                new BeanPropertyRowMapper<>(Passage.class),
                passage.getName()
        );

        if (passageValidacao.isEmpty()) {
            try {
                jdbcTemplate.update(
                        "INSERT INTO passage (name, region, type)" +
                                "VALUES (?, ?, ?)", passage.getName(), passage.getRegion(), passage.getType()
                );
            } catch (Exception e) {
                System.out.println(String.format("Erro ao inserir a passagem %s", passage.getName()));
            }
        } else {
            System.out.println(String.format("Passagem já cadastrada"));
        }
    }

    public void insertDirecao(Direction direction) {

        List<Direction> direcaoValidacao = jdbcTemplate.query(
                "SELECT * FROM direction WHERE nome = ?",
                new BeanPropertyRowMapper<>(Direction.class),
                direction.getName()
        );

        if (direcaoValidacao.isEmpty()) {
            try {
                Long passageId = jdbcTemplate.queryForObject(
                        "SELECT id_passage FROM passage WHERE nome = ?",
                        Long.class,
                        direction.getPassageName()
                );

                jdbcTemplate.update(
                        "INSERT INTO direction (name, fkPassage)" +
                                "VALUES (?, ?)", direction.getName(), passageId
                );
            } catch (Exception e) {
                System.out.println(String.format("Errou ao inserir direção %s", direction.getName()));
            }

        } else {
            System.out.println(String.format("Direção já cadastrada"));
        }
    }


    public void insertSegment(Segment segment) {

        List<Segment> segmentValidacao = jdbcTemplate.query(
                "SELECT * FROM segment WHERE nome = ?",
                new BeanPropertyRowMapper<>(Segment.class),
                segment.getNome()
        );

        if (segmentValidacao.isEmpty()) {
            try {
                Long directionId = jdbcTemplate.queryForObject(
                        "SELECT * FROM id_direction WHERE nome = ?",
                        Long.class,
                        segment.getDirectionName()
                );

                jdbcTemplate.update(
                        "INSERT INTO segment (name, fkDirection)" +
                                "VALUES (?, ?)", segment.getNome(), directionId
                );

            } catch (Exception e) {
                System.out.println(String.format("Errou ao inserir segmento %s", segment.getNome()));
            }
        } else {
            System.out.println(String.format("Segmento já cadastrado"));
        }
    }

    public void insertTimeStamp(TimeStamp timeStamp){
        try {
            Long segmentId = jdbcTemplate.queryForObject(
                    "SELECT id_segment FROM id_segment WHERE nome = ?",
                    Long.class,
                    timeStamp.getSegmentName()
            );

            jdbcTemplate.update(
                    "INSERT INTO segment (dataHorario, jamSize, fkSegment)" +
                            "VALUES (?, ?, ?)", timeStamp.getDataHorario(), timeStamp.getJamSize(), segmentId
            );
        } catch (Exception e){
            System.out.println(String.format("Errou ao inserir horário %s", timeStamp.getDataHorario()));
        }

        }

    }



