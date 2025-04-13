package school.sptech;

public class DadosTransito {

    private String passage;
    private String direction;
    private String type;
    private String region;
    private String timestamp;
    private Integer jamSize;
    private String segment;


    public DadosTransito(String passage, String direction, String type, String region, String timestamp, Integer jamSize, String segment) {
        this.passage = passage;
        this.direction = direction;
        this.type = type;
        this.region = region;
        this.timestamp = timestamp;
        this.jamSize = jamSize;
        this.segment = segment;
    }

}

