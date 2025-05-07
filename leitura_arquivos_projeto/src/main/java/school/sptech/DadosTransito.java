package school.sptech;

import java.time.LocalDateTime;

public class DadosTransito {

    private String passage;
    private String direction;
    private String type;
    private String region;
    private LocalDateTime timestamp;
    private Integer jamSize;
    private String segment;


    public DadosTransito(String passage, String direction, String type, String region, LocalDateTime timestamp, Integer jamSize, String segment) {
        this.passage = passage;
        this.direction = direction;
        this.type = type;
        this.region = region;
        this.timestamp = timestamp;
        this.jamSize = jamSize;
        this.segment = segment;
    }

    public DadosTransito() {
    }

    public String getPassage() {
        return passage;
    }

    public void setPassage(String passage) {
        this.passage = passage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
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

