package school.sptech.entidades;

import java.time.LocalDateTime;

public class TimeStamp {

    private Integer idTimeStamp;
    private LocalDateTime dataHorario;
    private String segmentName;
    private Integer jamSize;
    private Integer fkSegment;

    public TimeStamp() {
    }

    public TimeStamp(LocalDateTime dataHorario, Integer jamSize, Integer fkPassage) {
        this.dataHorario = dataHorario;
        this.jamSize = jamSize;
        this.fkSegment = fkPassage;
    }

    public TimeStamp(Integer idTimeStamp, LocalDateTime dataHorario, Integer jamSize, Integer fkPassage) {
        this.idTimeStamp = idTimeStamp;
        this.dataHorario = dataHorario;
        this.jamSize = jamSize;
        this.fkSegment = fkPassage;
    }

    public Integer getIdTimeStamp() {
        return idTimeStamp;
    }

    public void setIdTimeStamp(Integer idTimeStamp) {
        this.idTimeStamp = idTimeStamp;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Integer getJamSize() {
        return jamSize;
    }

    public void setJamSize(Integer jamSize) {
        this.jamSize = jamSize;
    }

    public Integer getFkSegment() {
        return fkSegment;
    }

    public void setFkSegment(Integer fkSegment) {
        this.fkSegment = fkSegment;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
}
