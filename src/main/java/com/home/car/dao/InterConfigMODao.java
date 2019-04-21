package com.home.car.dao;

import com.home.car.model.InterConfigMO;

public interface InterConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(InterConfigMO record);

    int insertSelective(InterConfigMO record);

    InterConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InterConfigMO record);

    int updateByPrimaryKey(InterConfigMO record);
}