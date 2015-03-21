/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class Friend implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String lastName;

    public Friend() {
    }

    public Friend(long id, String name, String lastName) {
        super();
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("fname", name);
            jsonObject.put("lname", lastName);
        } catch (Exception e) {
        }
        return jsonObject.toString();
    }
}
