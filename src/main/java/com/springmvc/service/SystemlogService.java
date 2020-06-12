package com.springmvc.service;

import com.springmvc.entity.Systemlog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ypl
 * @date 2020/5/19 - 21:53
 **/
@Service("systemlogService")
public interface SystemlogService {
    List<Systemlog> select(String userid);

    boolean deleteById(String id);

    List<Systemlog> selectOne(String id);

    boolean insertOne(Systemlog systemlog);

    List<Systemlog> selectAll();
}
