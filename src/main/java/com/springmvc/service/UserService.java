package com.springmvc.service;

import com.springmvc.entity.Users;

import java.util.List;

/**
 * @author ypl
 * @date 2020/5/16 - 0:19
 **/
public interface UserService {
    List<Users> select();

    void insertAll(List<Users> users);

    List<Users> select(String first, String end);

    boolean deleteById(String id);

    boolean updateById(Users users);

    List<Users> selectById(String id);

    boolean insertOne(Users users);

    Users selectOne(String id);

    List<Users> login(String id, String password);
}
