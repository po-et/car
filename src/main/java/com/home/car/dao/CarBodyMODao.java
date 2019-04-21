package com.home.car.dao;

import com.home.car.model.CarBodyMO;

import java.util.List;

public interface CarBodyMODao {

    List<CarBodyMO> selectAll();

    List<CarBodyMO> selectAllGroupByBrandId();

    int deleteByPrimaryKey(Long id);

    int insert(CarBodyMO record);

    int insertSelective(CarBodyMO record);

    CarBodyMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarBodyMO record);

    int updateByPrimaryKey(CarBodyMO record);
}