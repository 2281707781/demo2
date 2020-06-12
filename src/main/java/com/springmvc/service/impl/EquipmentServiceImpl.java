package com.springmvc.service.impl;

import com.springmvc.controller.EquipmentController;
import com.springmvc.dao.EquipmentMapper;
import com.springmvc.entity.Equipment;
import com.springmvc.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:54
 **/
@Service("equipmentService")
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public Equipment selectOneById(String id) {
        return equipmentMapper.selectOnrById(id);
    }

    @Override
    public List<Equipment> selectAll() {
        return equipmentMapper.selectAll();
    }

    @Override
    public List<Equipment> selectOneByid(String id) {
        return equipmentMapper.selectOneById(id);
    }

    @Override
    public boolean insertOne(Equipment equipment) {
        return equipmentMapper.insertOne(equipment);
    }

    @Override
    public boolean updateOne(Equipment equipment) {
        return equipmentMapper.updateOne(equipment);
    }

    @Override
    public boolean insertAll(List<Equipment> equipments) {
        return equipmentMapper.insertAll(equipments);
    }

    @Override
    public boolean deleteOne(Object id) {
        return  true;
    }
}