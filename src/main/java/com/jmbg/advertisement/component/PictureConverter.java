package com.jmbg.advertisement.component;
import com.jmbg.advertisement.entity.Picture;
import com.jmbg.advertisement.model.PictureModel;
import com.jmbg.advertisement.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PictureConverter {

    @Autowired
    private PictureService pictureService;

    public Picture modelToEntity(PictureModel model) {
        Picture picture = new Picture();
        picture.setId(model.getId());
        picture.setUrl(model.getUrl());
        picture.setQuality(model.getQuality());
        return picture;
    }

    public PictureModel entityToModel(Picture picture) {
        PictureModel model = new PictureModel();
        model.setId(picture.getId());
        model.setUrl(picture.getUrl());
        model.setQuality(picture.getQuality());
        return model;
    }
}
