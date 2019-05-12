package com.jmbg.advertisement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "advertisement")
public class ApplicationConfig {
    private Integer irrelevantMinScore;
    private List<String> typologies = new ArrayList<>();
    private ScoresConfig scores = new ScoresConfig();

    public Integer getIrrelevantMinScore() {
        return irrelevantMinScore;
    }

    public void setIrrelevantMinScore(Integer irrelevantMinScore) {
        this.irrelevantMinScore = irrelevantMinScore;
    }

    public List<String> getTypologies() {
        return typologies;
    }

    public void setTypologies(List<String> typologies) {
        this.typologies = typologies;
    }

    public ScoresConfig getScores() {
        return scores;
    }

    public void setScores(ScoresConfig scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "ApplicationConfig{" +
                "typologies=" + typologies +
                ", scores=" + scores +
                '}';
    }

    public class ScoresConfig {
        private Integer noPictures;
        private List<String> pictures = new ArrayList<>();
        private Integer haveDescription;
        private List<String> descriptionContains = new ArrayList<>();
        private List<String> descriptionLengths = new ArrayList<>();
        private List<String> adComplete = new ArrayList<>();

        public Integer getNoPictures() {
            return noPictures;
        }

        public List<String> getPictures() {
            return pictures;
        }

        public Map<String, Integer> getPicturesMap() {
            return this.pictures
                    .stream()
                    .map(element -> element.split(":"))
                    .collect(Collectors.toMap(element -> element[0], element -> Integer.parseInt(element[0])));
        }

        public Integer getHaveDescription() {
            return haveDescription;
        }

        public List<String> getDescriptionContains() {
            return descriptionContains;
        }

        public Map<String, Integer> getDescriptionContainsMap() {
            return this.descriptionContains
                    .stream()
                    .map(element -> element.split(":"))
                    .collect(Collectors.toMap(element -> element[0], element -> Integer.parseInt(element[1])));
        }

        public List<String> getDescriptionLengths() {
            return descriptionLengths;
        }

        public List<String> getAdComplete() {
            return adComplete;
        }

        public void setNoPictures(Integer noPictures) {
            this.noPictures = noPictures;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
        }

        public void setHaveDescription(Integer haveDescription) {
            this.haveDescription = haveDescription;
        }

        public void setDescriptionContains(List<String> descriptionContains) {
            this.descriptionContains = descriptionContains;
        }

        public void setDescriptionLengths(List<String> descriptionLengths) {
            this.descriptionLengths = descriptionLengths;
        }

        public void setAdComplete(List<String> adComplete) {
            this.adComplete = adComplete;
        }

        @Override
        public String toString() {
            return "ScoresConfig{" +
                    "noPictures=" + noPictures +
                    ", pictures=" + pictures +
                    ", haveDescription=" + haveDescription +
                    ", descriptionContains=" + descriptionContains +
                    ", descriptionLengths=" + descriptionLengths +
                    ", adComplete=" + adComplete +
                    '}';
        }
    }
}
