package com.home.car.dao;

import com.home.car.model.TechConfigMO;

public interface TechConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(TechConfigMO record);

    int insertSelective(TechConfigMO record);

    TechConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TechConfigMO record);

    int updateByPrimaryKey(TechConfigMO record);
}