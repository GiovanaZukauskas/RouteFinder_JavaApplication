package school.sptech;

public class Segment {

    private Long idSegment;
    private String nome;
    private String directionName;
    private Long fkDirection;

    public Segment() {
    }

    public Segment(String nome, Long fkDirection) {
        this.nome = nome;
        this.fkDirection = fkDirection;
    }

    public Segment(Long idSegment, String nome, Long fkDirection) {
        this.idSegment = idSegment;
        this.nome = nome;
        this.fkDirection = fkDirection;
    }

    public Long getIdSegment() {
        return idSegment;
    }

    public void setIdSegment(Long idSegment) {
        this.idSegment = idSegment;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getFkDirection() {
        return fkDirection;
    }

    public void setFkDirection(Long fkDirection) {
        this.fkDirection = fkDirection;
    }

    public String getDirectionName() {
        return directionName;
    }

    public void setDirectionName(String directionName) {
        this.directionName = directionName;
    }
}
