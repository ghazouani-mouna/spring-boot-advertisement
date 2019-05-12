package com.jmbg.advertisement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmbg.advertisement.model.AdvertisementModel;
import com.jmbg.advertisement.model.PictureModel;
import com.jmbg.advertisement.service.AdvertisementService;
import com.jmbg.advertisement.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.io.InputStream;
import java.util.List;

public class DBInit implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DBInit.class);

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AdvertisementService advertisementService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting DB Init!");
        loadPictures();
        loadAdvertisements();
    }

    private void loadPictures () throws Exception{
        logger.info("Loading Pictures");
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<PictureModel>> typeReference = new TypeReference<List<PictureModel>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/pictures.json");
        try {
            List<PictureModel> pictures = mapper.readValue(inputStream, typeReference);
            pictures.forEach(pictureService::create);
            logger.info("Number of pictures created: " + pictures.size());
        } catch (Exception e){
            logger.error("Unable to create pictures: " + e.getMessage());
        }
    }

    private void loadAdvertisements () throws Exception{
        logger.info("Loading Advertisements");
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<AdvertisementModel>> typeReference = new TypeReference<List<AdvertisementModel>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/advertisements.json");
        try {
            List<AdvertisementModel> advertisements = mapper.readValue(inputStream, typeReference);
            advertisements.forEach(advertisementService::create);
            logger.info("Number of advertisements created: " + advertisements.size());
        } catch (Exception e){
            logger.error("Unable to create advertisements: " + e.getMessage());
            throw e;
        }
    }
}
