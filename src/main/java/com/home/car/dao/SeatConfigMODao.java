package com.home.car.dao;

import com.home.car.model.SeatConfigMO;

public interface SeatConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(SeatConfigMO record);

    int insertSelective(SeatConfigMO record);

    SeatConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SeatConfigMO record);

    int updateByPrimaryKey(SeatConfigMO record);
}