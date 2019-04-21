package com.home.car.dao;

import com.home.car.model.EngineMO;

public interface EngineMODao {
    int deleteByPrimaryKey(Long id);

    int insert(EngineMO record);

    int insertSelective(EngineMO record);

    EngineMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EngineMO record);

    int updateByPrimaryKey(EngineMO record);
}