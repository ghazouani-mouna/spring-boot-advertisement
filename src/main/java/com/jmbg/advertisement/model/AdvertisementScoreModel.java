package com.jmbg.advertisement.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Comparator;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementScoreModel {
    private Integer id;

    private String description;

    private String typology;

    private Integer score;

    private Long irrelevantFrom;

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTypology() {
        return typology;
    }

    public Integer getScore() {
        return score;
    }

    public Long getIrrelevantFrom() {
        return irrelevantFrom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setIrrelevantFrom(Long irrelevantFrom) {
        this.irrelevantFrom = irrelevantFrom;
    }

    @Override
    public String toString() {
        return "AdvertisementScoreModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", typology='" + typology + '\'' +
                ", score=" + score +
                ", irrelevantFrom=" + irrelevantFrom +
                '}';
    }
}
