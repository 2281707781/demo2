package com.springmvc.service.impl;

import com.springmvc.dao.InformationMapper;
import com.springmvc.entity.Information;
import com.springmvc.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:55
 **/
@Service("informationService")
public class InformationServiceImpl implements InformationService {
    @Autowired
    private InformationMapper informationMapper;

    @Override
    public Information selectNewDate(String equipmentid) {
        return informationMapper.selectNewDate(equipmentid);
    }

    @Override
    public List<Information> selectSomeById(Date date1, Date date2, String id) {
        return informationMapper.selectSomeById(date1,date2,id);
    }

    @Override
    public int insertOne(Information information) {
        return informationMapper.insertOne(information);
    }

    @Override
    public int insertAll(List<Information> informations) {
        return informationMapper.insertAll(informations);
    }

    @Override
    public boolean deleteAll(Date dateTime, Date endTime, String id) {
        return informationMapper.delete(dateTime,endTime,id);
    }
}