package com.home.car.dto;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

/**
 * home vo
 */
@Data
public class Car {

    private String brand;
    private List<CarInfo> infoList;

    @Data
    public static class CarInfo {
        private String seriesName;
        private Integer carId;
        private Integer seat;

        @Override
        public String toString() {
            return JSON.toJSONString(this);
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
