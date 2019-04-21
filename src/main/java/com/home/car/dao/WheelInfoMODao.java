package com.home.car.dao;

import com.home.car.model.WheelInfoMO;

public interface WheelInfoMODao {
    int deleteByPrimaryKey(Long id);

    int insert(WheelInfoMO record);

    int insertSelective(WheelInfoMO record);

    WheelInfoMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WheelInfoMO record);

    int updateByPrimaryKey(WheelInfoMO record);
}