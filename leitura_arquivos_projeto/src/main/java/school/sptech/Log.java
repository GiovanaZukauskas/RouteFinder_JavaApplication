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
  
    public abstract void inserirLog(String description);
}
