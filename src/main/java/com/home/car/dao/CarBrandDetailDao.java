package com.home.car.dao;

import com.home.car.model.CarBrandDetail;

import java.util.List;

public interface CarBrandDetailDao {

    List<CarBrandDetail> selectAll();

    int insert(CarBrandDetail record);

    int insertList(List<CarBrandDetail> record);

    int insertSelective(CarBrandDetail record);

    CarBrandDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarBrandDetail record);

    int updateByPrimaryKey(CarBrandDetail record);
}