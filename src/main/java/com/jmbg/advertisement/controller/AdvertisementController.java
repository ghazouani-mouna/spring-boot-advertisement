package com.jmbg.advertisement.controller;

import com.jmbg.advertisement.model.AdvertisementModel;
import com.jmbg.advertisement.model.AdvertisementScoreModel;
import com.jmbg.advertisement.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdvertisementController {

    @Autowired
    private AdvertisementService service;

    @GetMapping(value = "/advertisement/calculate-scores")
    public List<AdvertisementScoreModel> calculateScores (){
        return this.service.calculateScores();
    }

    @GetMapping(value = "/advertisement/all")
    public List<AdvertisementModel> findAll (@RequestParam(required = false, defaultValue = "all") String show, @RequestParam(required = false) String sort){
        boolean relevants = show.equals("all") || show.equals("relevants") ? true : false;
        boolean irrelevants = show.equals("all") || show.equals("irrelevants") ? true : false;
        return this.findAdvertisements(relevants, irrelevants, sort);
    }

    @GetMapping(value = "/advertisement")
    public List<AdvertisementModel> findAllRelevants (@RequestParam(required = false) String sort){
        return this.findAdvertisements(true, false, sort);
    }

    private List<AdvertisementModel> findAdvertisements(Boolean relevants, Boolean irrelevants, String sort) {
        String sortField = null, sortOrder = null;
        if (sort != null){
            String sortInfo[] = sort.split(":");
            sortField = sortInfo.length > 0 ? sortInfo[0] : null;
            sortOrder = sortInfo.length > 1 ? sortInfo[1] : "desc";
        }
        return service.findAll(relevants, irrelevants, sortField, sortOrder);
    }
}