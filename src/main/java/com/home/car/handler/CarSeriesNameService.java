package com.home.car.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.home.car.dao.CarBrandDetailDao;
import com.home.car.dao.CarSeriesNameDao;
import com.home.car.dto.SerialNameDto;
import com.home.car.model.CarBrandDetail;
import com.home.car.model.CarSeriesName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

import static com.home.car.enums.UrlConstant.APP_KEY;
import static com.home.car.enums.UrlConstant.SERIES_NAME_URL;

/**
 * 车型查询
 *
 * @Author: poet
 * @Date: 2019-04-20
 */
@Slf4j
@Service
public class CarSeriesNameService {

    @Autowired
    CarBrandDetailDao carBrandDetailDao;
    @Autowired
    CarSeriesNameDao carSeriesNameDao;

    public Integer saveSeriesNameList(List<CarSeriesName> seriesNameList) throws Exception {

        if (CollectionUtils.isEmpty(seriesNameList)) {
            return 0;
        }
        Integer result = carSeriesNameDao.insertList(seriesNameList);
        log.info("INSERT seriesNameList: {}", result);
        return result;
    }


    public List<CarSeriesName> getSeriesNameList() throws Exception {
        //init
        List<CarSeriesName> seriesNameList = Lists.newLinkedList();

        List<CarBrandDetail> carBrandDetails = carBrandDetailDao.selectAll();

        for (CarBrandDetail v : carBrandDetails) {
            String carType = v.getCarType();

            // url req data
            Map<String, String> urlParam = Maps.newHashMap();
            urlParam.put("key", APP_KEY);
            urlParam.put("name", carType);

            HttpRequest request = HttpRequest.get(SERIES_NAME_URL).form(urlParam);
            String resp = request.body();
            log.info("http serial name, code: {}, msg: {}", request.code(), resp);

            // parse json
            if (request.code() == 200) {

                JSONObject jsonObject = JSON.parseObject(resp);
                if ("200".equals(jsonObject.getString("retCode"))) {

                    JSONArray array = jsonObject.getJSONArray("result");
                    int size = array.size();

                    for (int i = 0; i < size; i++) {

                        SerialNameDto dto = array.getObject(i, SerialNameDto.class);

                        CarSeriesName seriesName = new CarSeriesName();
                        seriesNameList.add(seriesName);

                        seriesName.setSeriesName(dto.getSeriesName());
                        seriesName.setCarId(dto.getCarId());
                        seriesName.setGuidePrice(dto.getGuidePrice());
                        seriesName.setBrandName(dto.getBrandName());
                        seriesName.setBrandId(v.getId());
                        seriesName.setParentId(v.getParentId());
                    }

                } else {
                    log.error("http failed! carType: {}, code: {}", carType, request.code());
                }
            } else {
                log.info("查询失败！ carType: {} ", carType);
            }
        }

        return seriesNameList;
    }


}
