package com.home.car.controller;

import com.home.car.handler.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/stat")
public class DemoController {

    @Autowired
    private StatService statCarriage;

    @RequestMapping(value = "/carriage")
    public String statCarriage() {
        List<String> results = statCarriage.statCarriage();
        statCarriage.saveTargetFile(results, "target_carriage");
        return "ok";
    }

    @RequestMapping(value = "/imExportComp")
    public String statImExportComp() {
        List<String> results = statCarriage.statImExportComp();
        statCarriage.saveTargetFile(results, "target_imExportComp");
        return "ok";
    }
}