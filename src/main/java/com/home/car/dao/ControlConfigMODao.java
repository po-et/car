package com.home.car.dao;

import com.home.car.model.ControlConfigMO;

public interface ControlConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(ControlConfigMO record);

    int insertSelective(ControlConfigMO record);

    ControlConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ControlConfigMO record);

    int updateByPrimaryKey(ControlConfigMO record);
}