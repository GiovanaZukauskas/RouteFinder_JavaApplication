package school.sptech;

import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

public class Log {
    private String category;
    private String description;
    static ConexaoBanco conexaoBanco = new ConexaoBanco();
    private static final JdbcTemplate jdbcTemplate = conexaoBanco.getJdbcTemplate();

    public Log(String category, String description) {
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Log{" +
                "category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static void inserirLog(String category, String description){
        if(category == null || description == null){
            return;
        }
        try {
            jdbcTemplate.update(
                    "INSERT INTO log (description,fk_alert,fk_category)" +
                            "VALUES (?, 1, ?)", description, 1
            );
        } catch (Exception e) {
            System.out.println(String.format("Erro ao inserir o Log %s", new Log(category, description)));
        }

    }
}
