package com.springmvc.dao;

import com.springmvc.entity.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:50
 **/
public interface EquipmentMapper {
    int insert(Equipment record);

    List<Equipment> selectAll();

    Equipment selectOnrById(@Param("id") String id);

    List<Equipment> selectOneById(@Param("id") String id);

    boolean insertOne(Equipment equipment);

    boolean updateOne(Equipment equipment);

    boolean insertAll(List<Equipment> equipments);
}
