package com.jmbg.advertisement.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmbg.advertisement.entity.Advertisement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreHelperTest {

    private List<Advertisement> advertisements;

    @Before
    public void init() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Advertisement>> typeReference = new TypeReference<List<Advertisement>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/exampleData.json");
        this.advertisements = mapper.readValue(inputStream, typeReference);
    }

    @Test
    public void testScoreCalculation() throws Exception {
        for (Advertisement advertisement : advertisements) {
            int score = ScoreHelper.calculateAdScore(advertisement);
            Assert.assertTrue(score == advertisement.getScore());
        }
    }
}
