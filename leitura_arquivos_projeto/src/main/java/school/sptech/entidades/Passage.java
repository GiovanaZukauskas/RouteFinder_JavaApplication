package school.sptech.entidades;

public class Passage {

    private Integer idPassage;
    private String name;
    private String region;
    private String type;

    public Passage() {
    }

    public Passage(String name, String region, String type) {
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public Passage(Integer idPassage, String name, String region, String type) {
        this.idPassage = idPassage;
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public Integer getIdPassage() {
        return idPassage;
    }

    public void setIdPassage(Integer idPassage) {
        this.idPassage = idPassage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
