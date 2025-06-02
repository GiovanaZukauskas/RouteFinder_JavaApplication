package school.sptech;

public class Categoria {

    private TipoCategoria tipo;
    private String description;

    public Categoria(TipoCategoria tipo, String description) {
        this.tipo = tipo;
        this.description = description;
    }

    public TipoCategoria getTipo() {
        return tipo;
    }

    public void setTipo(TipoCategoria tipo) {
        this.tipo = tipo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
