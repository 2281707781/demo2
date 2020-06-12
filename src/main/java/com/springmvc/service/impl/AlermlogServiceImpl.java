package com.springmvc.service.impl;

import com.springmvc.dao.AlermlogMapper;
import com.springmvc.entity.Alermlog;
import com.springmvc.service.AlermlogService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypl
 * @date 2020/6/7 - 21:05
 **/
@Service("alermlogService")
public class AlermlogServiceImpl implements AlermlogService {
    @Autowired
    private AlermlogMapper alermlogMapper;
    @Override
    public List<Alermlog> selectAll() {
        return alermlogMapper.selectAll();
    }

    @Override
    public boolean insert(Alermlog alermlog) {
        return alermlogMapper.insert(alermlog);
    }

    @Override
    public boolean delete(int id) {
        return alermlogMapper.delete(id);
    }
}