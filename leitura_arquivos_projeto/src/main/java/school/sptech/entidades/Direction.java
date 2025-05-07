package school.sptech.entidades;

public class Direction {

    private Integer idDirection;
    private String name;
    private String passageName;
    private Integer fkPassage;


    public Direction() {
    }

    public Direction(String name, Integer fkPassage) {
        this.name = name;
        this.fkPassage = fkPassage;
    }

    public Direction(Integer idDirection, String name, Integer fkPassage) {
        this.idDirection = idDirection;
        this.name = name;
        this.fkPassage = fkPassage;
    }

    public Integer getIdDirection() {
        return idDirection;
    }

    public void setIdDirection(Integer idDirection) {
        this.idDirection = idDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFkPassage() {
        return fkPassage;
    }

    public void setFkPassage(Integer fkPassage) {
        this.fkPassage = fkPassage;
    }

    public String getPassageName() {
        return passageName;
    }

    public void setPassageName(String passageName) {
        this.passageName = passageName;
    }
}
