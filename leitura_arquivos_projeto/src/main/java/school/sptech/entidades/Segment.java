package school.sptech.entidades;

public class Segment {

    private Integer idSegment;
    private String nome;
    private String directionName;
    private Integer fkDirection;

    public Segment() {
    }

    public Segment(String nome, Integer fkDirection) {
        this.nome = nome;
        this.fkDirection = fkDirection;
    }

    public Segment(Integer idSegment, String nome, Integer fkDirection) {
        this.idSegment = idSegment;
        this.nome = nome;
        this.fkDirection = fkDirection;
    }

    public Integer getIdSegment() {
        return idSegment;
    }

    public void setIdSegment(Integer idSegment) {
        this.idSegment = idSegment;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getFkDirection() {
        return fkDirection;
    }

    public void setFkDirection(Integer fkDirection) {
        this.fkDirection = fkDirection;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }
}
