package com.home.car.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.home.car.dto.Car;
import com.home.car.dto.KeyValueDto;
import com.home.car.dto.SerialNameDto;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * home home handler
 */
@Slf4j
public class CarSeatHandler {

    private static final String SERIES_NAME_URL = "http://apicloud.mob.com/home/seriesname/query";
    private static final String SERIES_ID_URL = "http://apicloud.mob.com/home/series/query";
    private static final String APP_KEY = "2aab26087429c";


    public static List<Car> assembleVO() {

        //init
        List<Car> carList = Lists.newLinkedList();
        Set<String> brandSet = Sets.newHashSet();
        Set<String> mbrandSet = Sets.newHashSet();
        int index=1;
        int match=1;
        int unmatch=1;

        // get names from files
        String filePath = System.getProperty("user.dir") + "/files/car_list.txt";
        File file = new File(filePath);

        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file), "UTF-8");

            for (String v : lines) {

                // url req data
                Map<String, String> urlParam = Maps.newHashMap();
                urlParam.put("key", APP_KEY);
                urlParam.put("name", v);
//            urlParam.put("name", "自由舰");

                HttpRequest request = HttpRequest.get(SERIES_NAME_URL).form(urlParam);
                String resp = request.body();
                log.info("http serial name, code: {}, msg: {}", request.code(), resp);

                // parse json
                if (request.code() == 200) {

                    JSONObject jsonObject = JSON.parseObject(resp);
                    if ("200".equals(jsonObject.getString("retCode"))) {

                        match++;
                        mbrandSet.add(v);
                        saveUnmatchBrand2File(v, "match_brand");

                        JSONArray array = jsonObject.getJSONArray("result");
                        int size = array.size();

                        List<Car.CarInfo> carInfoList = Lists.newArrayList();

                        for (int i = 0; i < size; i++) {
                            SerialNameDto dto = array.getObject(i, SerialNameDto.class);

                            Car.CarInfo carInfo = new Car.CarInfo();
                            carInfo.setSeriesName(dto.getSeriesName());
                            carInfo.setCarId(dto.getCarId());
                            carInfo.setSeat(assembleSeat(dto.getCarId()));
                            carInfoList.add(carInfo);
                        }

                        // assemble home
                        Car car = new Car();
                        car.setBrand(v);
                        car.setInfoList(carInfoList);
                        carList.add(car);

                        // save home
                        log.info("home: {}", car);
                        saveCar(car, "res_car");

                        // find not only 5 seats
                        saveNotOnly5Seats(car);


                    } else {
                        log.error("failed! brand: {}", v);
                        unmatch++;
                        saveUnmatchBrand2File(v, "unmatch_brand");
                        brandSet.add(v);
                    }
                } else {
                    log.info("查询失败！ count: " + index++);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("[查询失败] count: " + index);
        log.info("[match] count: " + match);
        log.info("[unmatch] count: " + unmatch);

        log.info("match brand: {}", mbrandSet);
        log.info("unmatch brand: {}", brandSet);

        log.info("respList: {}", carList);
        log.info("respList-json: {}", JSON.toJSONString(carList));

        return carList;
    }


    public static Integer assembleSeat(Integer carId) {

        // url req data
        Map<String, String> urlParam = Maps.newHashMap();
        urlParam.put("key", APP_KEY);
        urlParam.put("cid", carId + "");
//                urlParam.put("cid", "1790125");

        HttpRequest request = HttpRequest.get(SERIES_ID_URL).form(urlParam);
        String resp = request.body();
        log.info("http serial id, code: {}, msg: {}", request.code(), resp);

        // parse json
        if (request.code() == 200) {

            JSONObject jsonObject = JSON.parseObject(resp);
            if ("200".equals(jsonObject.getString("retCode"))) {

                JSONArray resultArray = jsonObject.getJSONArray("result");
                JSONArray carBodyArray = resultArray.getJSONObject(0).getJSONArray("carbody");
                KeyValueDto dto = carBodyArray.getObject(10, KeyValueDto.class);

                // special case: 7/8/11 or "7-11"
                if (!StringUtils.isNumeric(dto.getValue())) {
                        return -1;
                }
                return Integer.valueOf(dto.getValue());
            }
        } else {
            log.error("failed! " + request.code());
        }

        return -1;
    }

    public static void saveCar(Car car, String filename) {

        try {
            String filePath = System.getProperty("user.dir") + "/files/" + filename + ".txt";
            File file = new File(filePath);

            FileUtils.writeStringToFile(file, car.getBrand() + "\n", "utf-8", true);

            for (Car.CarInfo info : car.getInfoList()) {
                String content = String.format("    %s: %d", info.getSeriesName(), info.getSeat());
                FileUtils.writeStringToFile(file, content + "\n", "utf-8", true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveCarList(List<Car> carList) {

        try {
            String filePath = System.getProperty("user.dir") + "/files/car_result.json";
            File file = new File(filePath);

            String json = JSON.toJSONString(carList);
            FileUtils.writeStringToFile(file, json , "utf-8", true);

        } catch (Exception e) {
            e.printStackTrace();
        }

//        for (Car home : carList) {
//            saveCar(home, filename);
//        }
    }

    private static void saveNotOnly5Seats(Car car) {

        List<Car.CarInfo> carInfos = car.getInfoList();

        if (CollectionUtils.isEmpty(carInfos)) return;

        Map<Integer, List<Car.CarInfo>> seatMap = carInfos.stream().collect(Collectors.groupingBy(Car.CarInfo::getSeat));

        if (CollectionUtils.isEmpty(seatMap)) return;

        if (seatMap.size() == 1 && seatMap.containsKey(5)) {
            return;
        }

        Set<Integer> seats = seatMap.keySet();
//        seats.remove(-1);
        String seat = Joiner.on(",").skipNulls().join(seats);

        String content = String.format("%s: %s", car.getBrand(), seat);

        String filePath = System.getProperty("user.dir") + "/files/not_5.txt";
        File file = new File(filePath);
        try {
            FileUtils.writeStringToFile(file, content + "\n", "utf-8", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void saveUnmatchBrand2File(String brand, String fileName) {
        String filePath = System.getProperty("user.dir") + "/files/" + fileName + ".txt";
//        String filePath = System.getProperty("user.dir") + "/files/unmatch_brand.txt";
        File file = new File(filePath);
        try {
            FileUtils.writeStringToFile(file, brand + "\n", "utf-8", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            List<Car> carList = assembleVO();
            System.out.println("---------");
            saveCarList(carList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("---------");
    }




}
