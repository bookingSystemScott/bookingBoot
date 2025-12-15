package com.bookingsystem.bookingboot.mapper;

import com.bookingsystem.bookingboot.entity.Users;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
public interface UserMapper {

    @Select("select * from users where id = #{id} ")
    public Users findById(String id);

    List<Users> findAllUsers();
}
