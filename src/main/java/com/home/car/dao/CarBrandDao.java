package com.home.car.dao;

import com.home.car.model.CarBrand;

import java.util.List;

public interface CarBrandDao {

    int insert(CarBrand record);

    int insertList(List<CarBrand> record);

    int insertSelective(CarBrand record);

    CarBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarBrand record);

    int updateByPrimaryKey(CarBrand record);
}