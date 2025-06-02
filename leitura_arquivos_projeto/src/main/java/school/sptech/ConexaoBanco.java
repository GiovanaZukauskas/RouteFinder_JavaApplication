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

    private static JdbcTemplate jdbcTemplate;
    private final BasicDataSource basicDataSource;

    public ConexaoBanco() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(ConfigLoader.get("IP"));
        basicDataSource.setUsername("root");
        basicDataSource.setPassword(ConfigLoader.get("SENHA"));

        this.basicDataSource = basicDataSource;
        this.jdbcTemplate = new JdbcTemplate(basicDataSource);
    }


    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}



