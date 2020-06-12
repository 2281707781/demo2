package com.springmvc.dao;

import com.springmvc.entity.Alermlog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ypl
 * @date 2020/6/7 - 21:06
 **/
public interface AlermlogMapper {
    List<Alermlog> selectAll();

    boolean insert(Alermlog alermlog);

    boolean delete(@Param("id") int id);
}
