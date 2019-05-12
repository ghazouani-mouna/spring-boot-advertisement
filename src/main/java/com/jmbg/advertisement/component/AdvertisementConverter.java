package com.jmbg.advertisement.component;
import com.jmbg.advertisement.entity.Advertisement;
import com.jmbg.advertisement.model.AdvertisementModel;
import com.jmbg.advertisement.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AdvertisementConverter {

    @Autowired
    private PictureService pictureService;
    @Autowired
    private PictureConverter pictureConverter;

    public Advertisement modelToEntity(AdvertisementModel model) {
        Advertisement ad = new Advertisement();
        ad.setId(model.getId());
        ad.setDescription(model.getDescription());
        ad.setTypology(model.getTypology());
        ad.setHouseSize(model.getHouseSize());
        ad.setGardenSize(model.getGardenSize());
        ad.setIrrelevantFrom(model.getIrrelevantFrom());
        ad.setScore(model.getScore());
        ad.setPictures(model.getPicturesIds().stream().map(pictureService::findEntityById).collect(Collectors.toList()));
        return ad;
    }

    public AdvertisementModel entityToModel(Advertisement ad) {
        AdvertisementModel model = new AdvertisementModel();
        model.setId(ad.getId());
        model.setDescription(ad.getDescription());
        model.setTypology(ad.getTypology());
        model.setHouseSize(ad.getHouseSize());
        model.setGardenSize(ad.getGardenSize());
        model.setIrrelevantFrom(ad.getIrrelevantFrom());
        model.setScore(ad.getScore());
        model.setPictures(ad.getPictures().stream().map(pictureConverter::entityToModel).collect(Collectors.toList()));
        return model;
    }
}
