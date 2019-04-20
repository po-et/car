package com.home.car.dao;

import com.home.car.model.BaseInfoMO;

public interface BaseInfoMODao {
    int deleteByPrimaryKey(Long id);

    int insert(BaseInfoMO record);

    int insertSelective(BaseInfoMO record);

    BaseInfoMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseInfoMO record);

    int updateByPrimaryKey(BaseInfoMO record);
}