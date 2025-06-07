package school.sptech;

public class Error extends Log{

        private Alerta alerta;

    public Error() {
        super(new Categoria(TipoCategoria.ERROR, "Logs que mapeam eventos de erro!"));
    }

    @Override
    public void inserirLog(String nome, String description) {
        if(description == null){
            return;
        }
        try {
            getJdbcTemplate().update(
                    "INSERT INTO log (fk_category, description)" +
                            "VALUES (?, ?)", getCategory().getTipo().getFk(), description
            );
        } catch (Exception e) {
            System.out.println(String.format("Erro ao inserir o Log %s", e.getMessage()));
        }
        alerta.inserirAlerta();
    }
}
