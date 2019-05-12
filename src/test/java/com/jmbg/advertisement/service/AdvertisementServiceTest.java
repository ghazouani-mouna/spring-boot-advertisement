package com.jmbg.advertisement.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmbg.advertisement.entity.Advertisement;
import com.jmbg.advertisement.model.AdvertisementModel;
import com.jmbg.advertisement.repository.AdvertisementRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdvertisementServiceTest {

    @Autowired
    private AdvertisementService service;

    @MockBean
    private AdvertisementRepository repository;

    private List<Advertisement> advertisements;

    @Before
    public void setup() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Advertisement>> typeReference = new TypeReference<List<Advertisement>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/exampleData.json");
        this.advertisements = mapper.readValue(inputStream, typeReference);
        Mockito.reset(repository);
        Mockito.when(repository.findAll()).thenReturn(this.advertisements);
    }

    @Test
    public void getAllAdvertisements() {
        List<AdvertisementModel> result = service.findAll(true, true, null, null);
        Assert.assertTrue(result.size() == this.advertisements.size());
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void getRelevantsAdvertisements() {
        List<AdvertisementModel> result = service.findAll(true, false, null, null);
        Assert.assertTrue(result.size() != this.advertisements.size());
        result.forEach(element -> Assert.assertTrue(element.getScore() >= 40));
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void getIrrelevantsAdvertisements() {
        List<AdvertisementModel> result = service.findAll(false, true, null, null);
        Assert.assertTrue(result.size() != this.advertisements.size());
        result.forEach(element -> Assert.assertTrue(element.getScore() < 40));
        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void createAdvertisment() {
        AdvertisementModel adModel = new AdvertisementModel();
        adModel.setId(1);
        adModel.setDescription("Some text description");
        adModel.setTypology("FLAT");
        adModel.setHouseSize(100);
        adModel.setPicturesIds(new ArrayList<>());
        service.create(adModel);
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Advertisement.class));
    }

}
