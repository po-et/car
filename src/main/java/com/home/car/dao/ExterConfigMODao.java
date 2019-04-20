package com.home.car.dao;

import com.home.car.model.ExterConfigMO;

public interface ExterConfigMODao {
    int deleteByPrimaryKey(Long id);

    int insert(ExterConfigMO record);

    int insertSelective(ExterConfigMO record);

    ExterConfigMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExterConfigMO record);

    int updateByPrimaryKey(ExterConfigMO record);
}