package com.bookingsystem.bookingboot.mapper;

import com.bookingsystem.bookingboot.entity.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("select * from users where id = #{id} ")
    Users findById(String id);

    @Select("select * from users where username = #{username} ")
    Users findByUserName(String userName);


    List<Users> findAllUsers();
}
