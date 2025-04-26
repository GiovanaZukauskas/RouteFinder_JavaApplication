package school.sptech;

public class Passage {

    private Long idPassage;
    private String name;
    private String region;
    private Integer type;

    public Passage() {
    }

    public Passage(String name, String region, Integer type) {
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public Passage(Long idPassage, String name, String region, Integer type) {
        this.idPassage = idPassage;
        this.name = name;
        this.region = region;
        this.type = type;
    }

    public Long getIdPassage() {
        return idPassage;
    }

    public void setIdPassage(Long idPassage) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
