package school.sptech;

public class DadosTransito {

    private String type;
    private String region;
    private Integer jamSize;
    private String segment;


    public DadosTransito(String type, String region, Integer jamSize, String segment) {
        this.type = type;
        this.region = region;
        this.jamSize = jamSize;
        this.segment = segment;
    }

    public DadosTransito() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getJamSize() {
        return jamSize;
    }

    public void setJamSize(Integer jamSize) {
        this.jamSize = jamSize;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }
}

