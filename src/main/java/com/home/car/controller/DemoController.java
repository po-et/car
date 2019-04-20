package com.home.car.controller;

import com.home.car.handler.CarBrandService;
import com.home.car.handler.CarSeriesNameService;
import com.home.car.handler.CarSeriesService;
import com.home.car.model.CarSeriesName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/car")
public class DemoController {

    @Autowired
    private CarBrandService carBrandService;
    @Autowired
    private CarSeriesNameService carSeriesNameService;
    @Autowired
    private CarSeriesService carSeriesService;

    @ResponseBody
    @RequestMapping(value = "/brand")
    public String addCar() {
        try {
            carBrandService.saveCarBrand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @ResponseBody
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

    @ResponseBody
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