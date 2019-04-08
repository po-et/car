package com.car.seat.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeyValueDto implements Serializable {

    private String name;
    private String value;
}
