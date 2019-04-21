package com.home.car.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.home.car.dao.CarBrandDao;
import com.home.car.dao.CarBrandDetailDao;
import com.home.car.model.CarBrand;
import com.home.car.model.CarBrandDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * 获取所有汽车品牌
 * @Author: poet
 * @Date: 2019-04-11
 */
@Slf4j
@Service
public class CarBrandService {

    @Autowired
    CarBrandDao carBrandDao;
    @Autowired
    CarBrandDetailDao carBrandDetailDao;

    /**
     * 保存所有汽车名单
     * @throws Exception
     */
    public void saveCarBrand() throws Exception{

        // init
        List<CarBrand> carBrandList = Lists.newArrayList();
        List<CarBrandDetail> carBrandDetailList = Lists.newArrayList();
        Integer dbResult;

        File file = new File(System.getProperty("user.dir") + "/files/car_brand.json");

        String json = FileUtils.readFileToString(file, "UTF-8");

        JSONObject jsonObject = JSON.parseObject(json);

        String retCode = jsonObject.getString("retCode");
        log.info("retCode: {}, msg: {}", retCode, jsonObject.getString("msg"));

        JSONArray resultArry = jsonObject.getJSONArray("result");

        int size = resultArry.size();

        for (int i = 0; i < size; i++) {
            JSONObject result = resultArry.getJSONObject(i);

            CarBrand carBrand = new CarBrand();
            carBrand.setId((long) (i+1));
            carBrand.setBrand(result.getString("name"));
            carBrandList.add(carBrand);

            JSONArray sonArray = result.getJSONArray("son");

            int sonSize = sonArray.size();
            for (int j = 0; j < sonSize; j++) {

                JSONObject son = sonArray.getJSONObject(j);

                CarBrandDetail detail = new CarBrandDetail();
                carBrandDetailList.add(detail);

                detail.setParentId(carBrand.getId());
                detail.setParentBrand(carBrand.getBrand());
                detail.setCarBrand(son.getString("car"));
                detail.setCarType(son.getString("type"));
            }
        }

        dbResult = carBrandDao.insertList(carBrandList);
        log.info("INSERT CarBrand: {}", dbResult);

        dbResult = carBrandDetailDao.insertList(carBrandDetailList);
        log.info("INSERT CarBrandDetail: {}", dbResult);
    }


}
