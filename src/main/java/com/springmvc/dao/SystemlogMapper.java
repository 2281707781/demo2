package com.springmvc.dao;

import com.springmvc.entity.Systemlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SystemlogMapper {
    int insert(Systemlog record);

    int insertSelective(Systemlog record);

    List<Systemlog> select(@Param("userid") String userid);

    boolean deleteById(@Param("id") String id);

    List<Systemlog> selectOne(@Param("id") String id);

    boolean insertOne(Systemlog systemlog);
}