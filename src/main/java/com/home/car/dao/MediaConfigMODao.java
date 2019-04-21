package com.home.car.dao;

import com.home.car.model.MediaConfigMO;

public interface MediaConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(MediaConfigMO record);

    int insertSelective(MediaConfigMO record);

    MediaConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MediaConfigMO record);

    int updateByPrimaryKey(MediaConfigMO record);
}