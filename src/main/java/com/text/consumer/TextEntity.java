package com.text.consumer;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @author Sergii
 */
@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"kafkaOffset", "key"}))
public class TextEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long kafkaOffset;

    private String key;

    private String freqWord;

    private Double avgParagraphSize;

    private LocalTime avgParagraphProcessingTime;

    private LocalTime totalProcessingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKafkaOffset() {
        return kafkaOffset;
    }

    public void setKafkaOffset(Long kafkaOffset) {
        this.kafkaOffset = kafkaOffset;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFreqWord() {
        return freqWord;
    }

    public void setFreqWord(String freqWord) {
        this.freqWord = freqWord;
    }

    public Double getAvgParagraphSize() {
        return avgParagraphSize;
    }

    public void setAvgParagraphSize(Double avgParagraphSize) {
        this.avgParagraphSize = avgParagraphSize;
    }

    public LocalTime getAvgParagraphProcessingTime() {
        return avgParagraphProcessingTime;
    }

    public void setAvgParagraphProcessingTime(LocalTime avgParagraphProcessingTime) {
        this.avgParagraphProcessingTime = avgParagraphProcessingTime;
    }

    public LocalTime getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(LocalTime totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }
}
