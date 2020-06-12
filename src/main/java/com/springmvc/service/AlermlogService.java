package com.springmvc.service;

import com.springmvc.entity.Alermlog;

import java.util.List;

/**
 * @author ypl
 * @date 2020/6/7 - 21:05
 **/
public interface AlermlogService {
    List<Alermlog> selectAll();

    boolean insert(Alermlog alermlog);

    boolean delete(int id);
}
