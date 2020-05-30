package com.springmvc.service;

import com.springmvc.entity.Information;

import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:54
 **/
public interface InformationService {
    Information selectNewDate(String equipmentid);

    List<Information> selectSomeById(Date date1, Date date2, String id);

    int insertOne(Information information);

    int insertAll(List<Information> informations);

    boolean deleteAll(Date dateTime, Date endTime, String id);
}
