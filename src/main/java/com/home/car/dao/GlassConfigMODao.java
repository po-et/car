package com.home.car.dao;

import com.home.car.model.GlassConfigMO;

public interface GlassConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(GlassConfigMO record);

    int insertSelective(GlassConfigMO record);

    GlassConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GlassConfigMO record);

    int updateByPrimaryKey(GlassConfigMO record);
}