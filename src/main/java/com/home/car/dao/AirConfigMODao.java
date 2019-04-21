package com.home.car.dao;

import com.home.car.model.AirConfigMO;

public interface AirConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(AirConfigMO record);

    int insertSelective(AirConfigMO record);

    AirConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AirConfigMO record);

    int updateByPrimaryKey(AirConfigMO record);
}