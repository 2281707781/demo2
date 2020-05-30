package com.springmvc.service.impl;

import com.springmvc.dao.UsersMapper;
import com.springmvc.entity.Users;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ypl
 * @date 2020/5/16 - 0:21
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users> select() {
        return usersMapper.select();
    }

    @Override
    public void insertAll(List<Users> users) {
        usersMapper.insertAll(users);
    }

    @Override
    public List<Users> select(String first, String end) {
        return usersMapper.selectSome(first,end);
    }

    @Override
    public boolean deleteById(String id) {
        return usersMapper.deleteById(id);
    }

    @Override
    public boolean updateById(Users users) {
        return usersMapper.updateById(users);
    }

    @Override
    public List<Users> selectById(String id) {
        return usersMapper.selectById(id);
    }

    @Override
    public boolean insertOne(Users users) {
        return usersMapper.insertOne(users);
    }

    @Override
    public Users selectOne(String id) {
        return usersMapper.selectOne(id);
    }

    @Override
    public List<Users> login(String id, String password) {
        return usersMapper.login(id,password);
    }
}