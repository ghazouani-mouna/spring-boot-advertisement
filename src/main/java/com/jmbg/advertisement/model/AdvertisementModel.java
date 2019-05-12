package com.jmbg.advertisement.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Comparator;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdvertisementModel {
    private Integer id;

    private String description;

    private String typology;

    private Integer houseSize;

    private Integer gardenSize;

    private List<PictureModel> pictures;

    private List<Integer> picturesIds;

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

    public List<Integer> getPicturesIds() {
        return picturesIds;
    }

    public void setPicturesIds(List<Integer> picturesIds) {
        this.picturesIds = picturesIds;
    }

    public List<PictureModel> getPictures() {
        return pictures;
    }

    public void setPictures(List<PictureModel> pictures) {
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

    public static Comparator<AdvertisementModel> getAdComparator(String field, String order) {
        if (field == null) {
            return null;
        }
        Comparator<AdvertisementModel> comparator = null;
        switch (field) {
            case "score":
                comparator = Comparator.comparing(ad -> ad.getScore());
                break;
            case "description":
                comparator = Comparator.comparing(ad -> ad.getDescription().toLowerCase());
                break;
            case "typology":
                comparator = Comparator.comparing(ad -> ad.getTypology().toLowerCase());
                break;
            case "houseSize":
                comparator = Comparator.comparing(ad -> ad.getHouseSize(), Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            case "gardenSize":
                comparator = Comparator.comparing(ad -> ad.getGardenSize(), Comparator.nullsFirst(Comparator.naturalOrder()));
                break;
            default:
                return null;
        }

        return order.equals("desc") ? comparator.reversed() : comparator;
    };

    @Override
    public String toString() {
        return "AdvertisementModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", typology='" + typology + '\'' +
                ", houseSize=" + houseSize +
                ", gardenSize=" + gardenSize +
                ", pictures=" + pictures +
                ", picturesIds=" + picturesIds +
                ", score=" + score +
                ", irrelevantFrom=" + irrelevantFrom +
                '}';
    }
}
