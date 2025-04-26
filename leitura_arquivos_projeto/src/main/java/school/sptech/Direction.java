package school.sptech;

public class Direction {

    private Long idDirection;
    private String name;
    private String passageName;
    private Long fkPassage;


    public Direction() {
    }

    public Direction(String name, Long fkPassage) {
        this.name = name;
        this.fkPassage = fkPassage;
    }

    public Direction(Long idDirection, String name, Long fkPassage) {
        this.idDirection = idDirection;
        this.name = name;
        this.fkPassage = fkPassage;
    }

    public Long getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Long idDirection) {
        this.idDirection = idDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFkPassage() {
        return fkPassage;
    }

    public void setFkPassage(Long fkPassage) {
        this.fkPassage = fkPassage;
    }

    public String getPassageName() {
        return passageName;
    }

    public void setPassageName(String passageName) {
        this.passageName = passageName;
    }
}
