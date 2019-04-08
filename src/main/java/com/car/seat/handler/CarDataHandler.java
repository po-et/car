package com.car.seat.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.car.seat.dto.Car;
import com.car.seat.util.ExcelUtil;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class CarDataHandler {

    public static void main(String[] args) {
        //init
        try {
            handleData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleData() {

        //init
        Map<String, String> seatMap = Maps.newHashMap();
        Map<String, String> seatTmpMap = Maps.newHashMap();
        File resultFile = new File(System.getProperty("user.dir") + "/files/final_result.txt");

        try {
            // get json object
            File file = new File(System.getProperty("user.dir") + "/files/car_result.json");
            List<String> lines = IOUtils.readLines(new FileInputStream(file), "UTF-8");

            System.out.println("size: " + lines.size());
            List<Car> carList = JSONObject.parseObject(lines.get(0), new TypeReference<List<Car>>() {});


            //assemble map
            carList.forEach(v -> {

                List<Car.CarInfo> infoList = v.getInfoList();
                Map<Integer, List<Car.CarInfo>> infoListMap = infoList.stream().collect(Collectors.groupingBy(Car.CarInfo::getSeat));

                Set<Integer> seats = infoListMap.keySet();
                String seat = Joiner.on(",").skipNulls().join(seats);

                if (seatMap.containsKey(v.getBrand())) {
                    seatTmpMap.put(v.getBrand()+ "---" +Math.random() * 10, seat);
                }
                seatMap.put(v.getBrand(), seat);
            });


            // get names from files
            File brandFile = new File(System.getProperty("user.dir") + "/files/car_list.txt");

            List<String> brandLines = IOUtils.readLines(new FileInputStream(brandFile), "UTF-8");

            brandLines.forEach(v -> {
                try {
                    if (seatMap.containsKey(v)) {
                        String content = seatMap.get(v) + "\n";
//                        String content = "\"" + v + "\" : \"" + seatMap.get(v) + "\"\n";
                        FileUtils.writeStringToFile(resultFile, content , "utf-8", true);

                    } else {
                        String content = "-1" + "\n";
//                        String content = "\"" + v + "\" : \"-1\"\n";
                        FileUtils.writeStringToFile(resultFile, content , "utf-8", true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}