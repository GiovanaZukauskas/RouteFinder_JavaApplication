package school.sptech;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

    public class ConexaoBanco {

        private final JdbcTemplate jdbcTemplate;
        private final BasicDataSource basicDataSource;

        public ConexaoBanco() {
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setUrl("jdbc:mysql://3.87.195.225:3306/trafego");
            basicDataSource.setUsername("routefinder");
            basicDataSource.setPassword("urubu100");

            this.basicDataSource = basicDataSource;
            this.jdbcTemplate = new JdbcTemplate(basicDataSource);
        }

        public BasicDataSource getBasicDataSource() {
            return basicDataSource;
        }

        public JdbcTemplate getJdbcTemplate() {

            return jdbcTemplate;
        }
    }

