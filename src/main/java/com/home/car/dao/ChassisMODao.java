package com.home.car.dao;

import com.home.car.model.ChassisMO;

public interface ChassisMODao {
    int deleteByPrimaryKey(Long id);

    int insert(ChassisMO record);

    int insertSelective(ChassisMO record);

    ChassisMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChassisMO record);

    int updateByPrimaryKey(ChassisMO record);
}