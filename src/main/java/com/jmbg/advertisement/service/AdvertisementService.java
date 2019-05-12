package com.jmbg.advertisement.service;

import com.jmbg.advertisement.component.AdvertisementConverter;
import com.jmbg.advertisement.component.AdvertisementScoreConverter;
import com.jmbg.advertisement.entity.Advertisement;
import com.jmbg.advertisement.helper.ScoreHelper;
import com.jmbg.advertisement.model.AdvertisementModel;
import com.jmbg.advertisement.model.AdvertisementScoreModel;
import com.jmbg.advertisement.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository repository;
    @Autowired
    private AdvertisementConverter converter;

    @Autowired
    private AdvertisementScoreConverter adScoreconverter;

    private final static int MIN_AD_SCORE = 40;

    public List<AdvertisementModel> findAll(Boolean relevants, Boolean irrelevants, String sortField, String sortOrder) {
        List<AdvertisementModel> advertisements = repository.findAll().stream()
                .parallel()
                .filter(ad -> (irrelevants && ad.getScore() < MIN_AD_SCORE) ||  (relevants && ad.getScore() >= MIN_AD_SCORE))
                .map(converter::entityToModel)
                .collect(Collectors.toList());
        Comparator<AdvertisementModel> comparator = AdvertisementModel.getAdComparator(sortField, sortOrder);
        if (comparator != null) {
            advertisements.sort(comparator);
        }
        return advertisements;
    }

    public Advertisement create(AdvertisementModel ad) {
        if (ad.getId() != null) {
            Optional<Advertisement> existedAd = repository.findById(ad.getId());
            if (existedAd.isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Advertisement with Id " + ad.getId() + " already exist");
            }
        }
        Advertisement adEntity = converter.modelToEntity(ad);
        adEntity.setScore(ScoreHelper.calculateAdScore(adEntity));
        adEntity.setIrrelevantFrom((adEntity.getScore() < 40) ? new Date().getTime() : null);
        return repository.save(adEntity);
    }

    public List<AdvertisementScoreModel> calculateScores() {
        return repository.findAll().stream()
                .parallel()
                .map(adScoreconverter::entityToModel)
                .collect(Collectors.toList());
    }
}
