package com.jmbg.advertisement.entity;


import com.jmbg.advertisement.entity.Picture;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advertisement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String description;

    @NotNull
    private String typology;

    private Integer houseSize;

    private Integer gardenSize;

    @ManyToMany
    @JoinTable(
            name="AD_PIC",
            joinColumns=@JoinColumn(name="AD_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="PIC_ID", referencedColumnName="ID"))
    private List<Picture> pictures = new ArrayList<>();

    private Integer score;

    private Long irrelevantFrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getIrrelevantFrom() {
        return irrelevantFrom;
    }

    public void setIrrelevantFrom(Long irrelevantFrom) {
        this.irrelevantFrom = irrelevantFrom;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", typology='" + typology + '\'' +
                ", houseSize=" + houseSize +
                ", gardenSize=" + gardenSize +
                ", pictures=" + pictures +
                ", score=" + score +
                ", irrelevantFrom=" + irrelevantFrom +
                '}';
    }
}
