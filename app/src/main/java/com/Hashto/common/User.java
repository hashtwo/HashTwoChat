/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private int hashtwo_id;
    private String name, lastName;
    private int sexe;
    private String bDay;
    private String city;
    private String email;
    private String password;
    private String pictureName;
    private String typeOfConnextion;

    public User() {
    }

    public User(int id, String name, String lastName, int sexe, String bDay,
                String city, String pictureName) {
        super();
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.sexe = sexe;
        this.bDay = bDay;
        this.city = city;
        this.pictureName = pictureName;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("fname", name);
            json.put("lname", lastName);
            json.put("gendre", sexe);
            json.put("bday", bDay);
            json.put("email", email);
            json.put("password", password);
            json.put("location", city);
            json.put("imgProfile", pictureName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public String getbDay() {
        return bDay;
    }

    public void setbDay(String bDay) {
        this.bDay = bDay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getHashtwo_id() {
        return hashtwo_id;
    }

    public void setHashtwo_id(int heloo_id) {
        this.hashtwo_id = hashtwo_id;
    }

    public String getTypeOfConnextion() {
        return typeOfConnextion;
    }

    public void setTypeOfConnextion(String typeOfConnextion) {
        this.typeOfConnextion = typeOfConnextion;
    }

}
