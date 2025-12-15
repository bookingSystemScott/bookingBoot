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

//    // getter / setter 全部寫出來
//
//    public String getId() { return id; }
//    public void setId(String id) { this.id = id; }
//
//    public String getUserName() { return userName; }
//    public void setUserName(String userName) { this.userName = userName; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getPasswordHash() { return passwordHash; }
//    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
//
//    public String getPhoneNumber() { return phoneNumber; }
//    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
//
//    public Date getCreatedAt() { return createdAt; }
//    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
//
//    public Date getUpdatedAt() { return updatedAt; }
//    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
//
//    @Override
//    public String toString() {
//        return "Users{" +
//                "id='" + id + '\'' +
//                ", userName='" + userName + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
