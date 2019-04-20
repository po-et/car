package com.home.car.dao;

import com.home.car.model.CarSeriesName;

import java.util.List;

public interface CarSeriesNameDao {

    List<CarSeriesName> selectAll();

    int insert(CarSeriesName record);

    int insertList(List<CarSeriesName> record);

    int updateCarImage(CarSeriesName record);


    int insertSelective(CarSeriesName record);

    CarSeriesName selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarSeriesName record);

}