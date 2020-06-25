package com.springmvc.service.impl;

import com.springmvc.dao.EquipmentMapper;
import com.springmvc.dao.InformationMapper;
import com.springmvc.entity.Equipment;
import com.springmvc.entity.Information;
import com.springmvc.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private EquipmentMapper equipmentMapper;

    Information information = new Information();
    @Override
    public Information selectNewDate(String equipmentid,String table) {
        information =  informationMapper.selectNewDate(equipmentid,table);
        information.setEquipment(equipmentMapper.selectOnrById(information.getEquipmentid()));
        return information;
    }

    @Override
    public List<Information> selectSomeById(Date date1, Date date2, String equipmentid,String table) {
        List<Information>  informations = informationMapper.selectSomeById(date1,date2,equipmentid,table);
        for (Information str : informations){
            str.setEquipment(equipmentMapper.selectOnrById(str.getEquipmentid()));
        }
        return informations;
    }

    @Override
    public int insertOne(Information information,String table) {
        return informationMapper.insertOne(information,table);
    }

    @Override
    public int insertAll(List<Information> informations,String table) {
        return informationMapper.insertAll(informations,table);
    }

    @Override
    public boolean deleteAll(Date dateTime, Date endTime, String equipmentid,String table) {
        return informationMapper.delete(dateTime,endTime,equipmentid,table);
    }

    @Override
    public boolean createTable(String id) {
        return informationMapper.createTable(id);

    }

    @Override
    public List<Information> selectAll() {
        List<Equipment> equipments = new ArrayList<>();
        equipments = equipmentMapper.selectAll();
        Information information = new Information();
        List<Information> informations = new ArrayList<Information>();
        for (int i=0;i<equipments.size();i++){
            information = informationMapper.selectNewDate(equipments.get(i).getId(),"information"+equipments.get(i).getId());
            information.setEquipment(equipments.get(i));
            System.out.println(information);
            informations.add(information);
        }
        return informations;
    }
}