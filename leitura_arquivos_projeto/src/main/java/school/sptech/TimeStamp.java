package school.sptech;

import java.time.LocalDateTime;

public class TimeStamp {

    private Long idTimeStamp;
    private LocalDateTime dataHorario;
    private String segmentName;
    private Long jamSize;
    private Long fkSegment;

    public TimeStamp() {
    }

    public TimeStamp(LocalDateTime dataHorario, Long jamSize, Long fkPassage) {
        this.dataHorario = dataHorario;
        this.jamSize = jamSize;
        this.fkSegment = fkPassage;
    }

    public TimeStamp(Long idTimeStamp, LocalDateTime dataHorario, Long jamSize, Long fkPassage) {
        this.idTimeStamp = idTimeStamp;
        this.dataHorario = dataHorario;
        this.jamSize = jamSize;
        this.fkSegment = fkPassage;
    }

    public Long getIdTimeStamp() {
        return idTimeStamp;
    }

    public void setIdTimeStamp(Long idTimeStamp) {
        this.idTimeStamp = idTimeStamp;
    }

    public LocalDateTime getDataHorario() {
        return dataHorario;
    }

    public void setDataHorario(LocalDateTime dataHorario) {
        this.dataHorario = dataHorario;
    }

    public Long getJamSize() {
        return jamSize;
    }

    public void setJamSize(Long jamSize) {
        this.jamSize = jamSize;
    }

    public Long getFkSegment() {
        return fkSegment;
    }

    public void setFkSegment(Long fkSegment) {
        this.fkSegment = fkSegment;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
}
