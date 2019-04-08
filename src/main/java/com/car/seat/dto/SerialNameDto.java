package com.car.seat.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SerialNameDto implements Serializable {

    private String brandName;
    private Integer carId;
    private String guidePrice;
    private String seriesName;
}
