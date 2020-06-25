package com.springmvc.service;

import com.springmvc.entity.Information;

import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:54
 **/
public interface InformationService {
    Information selectNewDate(String equipmentid,String table);

    List<Information> selectSomeById(Date date1, Date date2, String equipmentid,String table);

    int insertOne(Information information,String table);

    int insertAll(List<Information> informations,String table);

    boolean deleteAll(Date dateTime, Date endTime, String equipmentid ,String table);

    boolean createTable(String id);

    List<Information> selectAll();
}
