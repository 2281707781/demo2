package com.springmvc.service.impl;

import com.springmvc.dao.SystemlogMapper;
import com.springmvc.entity.Systemlog;
import com.springmvc.service.SystemlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:56
 **/
@Service("systemlogService")
public class SystemlogServiceImpl implements SystemlogService {
    @Autowired
    private SystemlogMapper systemlogMapper;

    @Override
    public List<Systemlog> select(String userid) {
        return systemlogMapper.select(userid);
    }

    @Override
    public boolean deleteById(String id) {
        return systemlogMapper.deleteById(id);
    }

    @Override
    public List<Systemlog> selectOne(String id) {
        return systemlogMapper.selectOne(id);
    }

    @Override
    public boolean insertOne(Systemlog systemlog) {
        return systemlogMapper.insertOne(systemlog);
    }
}