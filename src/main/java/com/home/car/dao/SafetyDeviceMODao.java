package com.home.car.dao;

import com.home.car.model.SafetyDeviceMO;

public interface SafetyDeviceMODao {
    int deleteByPrimaryKey(Long id);

    int insert(SafetyDeviceMO record);

    int insertSelective(SafetyDeviceMO record);

    SafetyDeviceMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SafetyDeviceMO record);

    int updateByPrimaryKey(SafetyDeviceMO record);
}