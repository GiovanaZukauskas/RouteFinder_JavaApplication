package school.sptech;

public enum TipoCategoria {

    INFO(1),
    ERROR(2);

    private final Integer fk;

    TipoCategoria(Integer fk) {
        this.fk = fk;
    }

    public Integer getFk() {
        return fk;
    }
}
