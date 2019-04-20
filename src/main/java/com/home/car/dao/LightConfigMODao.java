package com.home.car.dao;

import com.home.car.model.LightConfigMO;

public interface LightConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(LightConfigMO record);

    int insertSelective(LightConfigMO record);

    LightConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LightConfigMO record);

    int updateByPrimaryKey(LightConfigMO record);
}