package com.example.thelittlebaker;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private double age;
    private String email;
    private int acecssLevel;
    private String fbId;
    private List<Patisserie> lastOrder;



    private String OrderId;


    public User(String userName, String password, double age, String email,String id) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel = 0;
        this.fbId=id;
        this.lastOrder= new ArrayList<>();

    }


    public User(String userName, String password, double age, String email, int acecssLevel,String id) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel = acecssLevel;
        this.fbId=id;
        this.lastOrder= new ArrayList<>();

    }
    public User(String userName, String password, double age, String email, int acecssLevel) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel = acecssLevel;
        this.lastOrder= new ArrayList<>();
    }



    public User(String userName, String password, double age, String email) {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.acecssLevel = 0;
        this.lastOrder= new ArrayList<>();
    }


    public int getAcecssLevel() {
        return acecssLevel;
    }

    public void setAcecssLevel(int acecssLevel) {
        this.acecssLevel = acecssLevel;
    }

    public String getUserName() {
        return userName;
    }

    // public void setUserName(String userName) {
    //   this.userName = userName;
    //}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFbId() {
        return fbId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", acecssLevel=" + acecssLevel +
                '}';
    }
}
