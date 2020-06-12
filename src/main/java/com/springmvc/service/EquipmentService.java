package com.springmvc.service;

import com.springmvc.entity.Equipment;

import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:53
 **/
public interface EquipmentService {
    Equipment selectOneById(String id);

    List<Equipment> selectAll();

    List<Equipment> selectOneByid(String id);

    boolean insertOne(Equipment equipment);

    boolean updateOne(Equipment equipment);

    boolean insertAll(List<Equipment> equipments);

    boolean deleteOne(Object id);
}
