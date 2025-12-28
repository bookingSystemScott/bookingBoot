package com.bookingsystem.bookingboot.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Users {

    private String id;
    private String userName;
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private Date createdAt;
    private Date updatedAt;
    private String authority;

}
