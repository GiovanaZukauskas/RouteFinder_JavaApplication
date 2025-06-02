package school.sptech;

public class Info extends Log{

    public Info() {
        super(new Categoria(TipoCategoria.INFO, "Logs que mapeam eventos bem sucedidos!"));
    }

    @Override
    public void inserirLog(String description) {
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
            Error erro = new Error();
            erro.inserirLog(String.format("Erro ao inserir o Log %s", e.getMessage()));
        }

    }
}
