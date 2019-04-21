package com.home.car.controller;

import com.home.car.handler.CarBrandService;
import com.home.car.handler.CarSeriesNameService;
import com.home.car.handler.CarSeriesService;
import com.home.car.model.CarSeriesName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/db/car")
public class DbController {

    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarSeriesNameService carSeriesNameService;
    @Autowired
    private CarSeriesService carSeriesService;

    @RequestMapping(value = "/brand")
    public String addCar() {
        try {
            carBrandService.saveCarBrand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "/series/name")
    public Integer addSeriesName() {
        Integer result = 0;
        try {
            List<CarSeriesName> seriesNameList = carSeriesNameService.getSeriesNameList();
            result = carSeriesNameService.saveSeriesNameList(seriesNameList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/series")
    public String addSeries() {
        try {
            carSeriesService.saveCarAllInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

}