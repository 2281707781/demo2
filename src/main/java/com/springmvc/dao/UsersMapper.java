package com.springmvc.dao;

import com.springmvc.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int insert(Users record);

    int insertSelective(Users record);

    List<Users> select();

    void insertAll(List<Users> users);

    List<Users> selectSome(@Param("first") String first,@Param("end") String end);

    boolean deleteById(@Param("id") String id);

    boolean updateById(Users users);

    List<Users> selectById(@Param("id") String id);

    boolean insertOne(Users users);

    Users selectOne(@Param("id") String id);

    List<Users> login(@Param("id") String id,@Param("password") String password);
}