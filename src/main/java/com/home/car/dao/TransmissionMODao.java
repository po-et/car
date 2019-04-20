package com.home.car.dao;

import com.home.car.model.TransmissionMO;

public interface TransmissionMODao {
    int deleteByPrimaryKey(Long id);

    int insert(TransmissionMO record);

    int insertSelective(TransmissionMO record);

    TransmissionMO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TransmissionMO record);

    int updateByPrimaryKey(TransmissionMO record);
}