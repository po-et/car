package com.home.car.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 获取所有汽车品牌
 * @Author: poet
 * @Date: 2019-04-11
 */
@Slf4j
@Service
public class CarBrandService {

    /**
     * 保存所有汽车名单
     * @throws Exception
     */
    public void saveCarBrand() throws Exception{

        List<String> cars = Lists.newArrayList();


        File file = new File(System.getProperty("user.dir") + "/files/car_brand.json");

        String json = FileUtils.readFileToString(file, "UTF-8");

        JSONObject jsonObject = JSON.parseObject(json);

        String retCode = jsonObject.getString("retCode");

        JSONArray result = jsonObject.getJSONArray("result");

        int size = result.size();

        for (int i = 0; i < size; i++) {
            JSONObject object = result.getJSONObject(i);

//            String name =


        }

    }




}
