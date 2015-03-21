/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import org.json.JSONObject;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class Event {
    private long id;
    private String name;
    private String imgOfEvent;
    private String placeLocation;
    private String cityLocation;
    private String countryLocation;
    private String streetLocation;
    private String longitude;
    private String latitude;
    private String startDate;
    private String endDate;
    private String zipCode;

    public Event() {
    }

    public Event(int id, String name, String imgOfEvent, String placeLocation,
                 String cityLocation, String countryLocation, String streetLocation,
                 String longitude, String latitude, String startDate, String endDate, String zipCode) {
        super();
        this.id = id;
        this.name = name;
        this.imgOfEvent = imgOfEvent;
        this.placeLocation = placeLocation;
        this.cityLocation = cityLocation;
        this.countryLocation = countryLocation;
        this.streetLocation = streetLocation;
        this.longitude = longitude;
        this.latitude = latitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.zipCode = zipCode;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImgOfEvent() {
        return imgOfEvent;
    }

    public void setImgOfEvent(String imgOfEvent) {
        this.imgOfEvent = imgOfEvent;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    public String getStreetLocation() {
        return streetLocation;
    }

    public void setStreetLocation(String streetLocation) {
        this.streetLocation = streetLocation;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLaltitude() {
        return latitude;
    }

    public void setLaltitude(String laltitude) {
        this.latitude = laltitude;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("name", name);
            jsonObject.put("imgOfEvents", imgOfEvent);
            jsonObject.put("nameOfPlace", placeLocation);
            jsonObject.put("cityOfLocation", cityLocation);
            jsonObject.put("countryOfLocation", countryLocation);
            jsonObject.put("streetLocation", streetLocation);
            jsonObject.put("longitude", longitude);
            jsonObject.put("altitude", latitude);
            jsonObject.put("startDate", startDate);
            jsonObject.put("endDate", endDate);
            jsonObject.put("zipCode", zipCode);
        } catch (Exception e) {
        }
        return jsonObject.toString();
    }


}
