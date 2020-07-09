package com.springmvc.dao;

import com.springmvc.entity.Information;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InformationMapper {
    int insert(Information record);

    int insertSelective(Information record);

    Information selectNewDate(@Param("equipmentid") String equipmentid,@Param("table") String table);

    List<Information> selectSomeById(@Param("date1") Date date1, @Param("date2") Date date2, @Param("equipmentid") String equipment,@Param("table") String table);

    int insertOne(@Param("information") Information information,@Param("table") String table);

    int insertAll(List<Information> informations,@Param("table") String table);

    boolean delete(@Param("dateTime") Date dateTime,@Param("endTime") Date endTime,@Param("equipmentid") String equipment,@Param("table") String table);

    boolean createTable(@Param("id") String id);

    List<Information> selectAll();

    List<Information> selectByTime(@Param("m") Date m,@Param("s") String s);

    void deleteToTime(@Param("m") Date m,@Param("id") String id);

    boolean insertOnes(@Param("msg") String msg,@Param("start") Date start);
}