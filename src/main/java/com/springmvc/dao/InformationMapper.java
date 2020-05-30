package com.springmvc.dao;

import com.springmvc.entity.Information;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InformationMapper {
    int insert(Information record);

    int insertSelective(Information record);

    Information selectNewDate(@Param("equipmentid") String equipmentid);

    List<Information> selectSomeById(@Param("date1") Date date1, @Param("date2") Date date2, @Param("equipmentid") String equipment);

    int insertOne(Information information);

    int insertAll(List<Information> informations);

    boolean delete(@Param("dateTime") Date dateTime,@Param("endTime") Date endTime,@Param("equipmentid") String equipment);
}