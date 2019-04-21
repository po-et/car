package com.home.car.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerialNameDto implements Serializable {

    private String brandName;
    private Integer carId;
    private String guidePrice;
    private String seriesName;
}
