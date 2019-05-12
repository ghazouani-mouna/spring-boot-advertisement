package com.jmbg.advertisement.component;
import com.jmbg.advertisement.entity.Advertisement;
import com.jmbg.advertisement.helper.ScoreHelper;
import com.jmbg.advertisement.model.AdvertisementScoreModel;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementScoreConverter {

    public AdvertisementScoreModel entityToModel(Advertisement ad) {
        AdvertisementScoreModel model = new AdvertisementScoreModel();
        model.setId(ad.getId());
        model.setDescription(ad.getDescription());
        model.setTypology(ad.getTypology());
        model.setIrrelevantFrom(ad.getIrrelevantFrom());
        model.setScore(ScoreHelper.calculateAdScore(ad));
        return model;
    }
}
