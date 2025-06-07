package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public abstract class Log {
    private Categoria category;
    private final JdbcTemplate jdbcTemplate = ConexaoBanco.getJdbcTemplate();

    public Log(Categoria category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Log{" +
                "category='" + category + '\'' +
                '}';
    }

    public abstract void inserirLog(String nome, String description) throws Exception;

    public Categoria getCategory() {
        return category;
    }

    public void setCategory(Categoria category) {
        this.category = category;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
