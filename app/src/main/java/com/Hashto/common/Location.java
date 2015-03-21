/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class Location implements Serializable {
    private String idLocation;
    private double altitude;
    private double longitude;
    private String nameOfPlace, streetOfPlace, cityOfLocation, countryOfLocation;
    private int zipCode;

    public Location() {
        super();
    }

    public Location(String idLocation, double altitude, double longitude,
                    String nameOfPlace, String streetOfPlace, String cityOfLocation,
                    String countryOfLocation, int zipCode) {
        super();
        this.idLocation = idLocation;
        this.altitude = altitude;
        this.longitude = longitude;
        this.nameOfPlace = nameOfPlace;
        this.streetOfPlace = streetOfPlace;
        this.cityOfLocation = cityOfLocation;
        this.countryOfLocation = countryOfLocation;
        this.zipCode = zipCode;
    }

    public String getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(String idLocation) {
        this.idLocation = idLocation;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getNameOfPlace() {
        return nameOfPlace;
    }

    public void setNameOfPlace(String nameOfPlace) {
        this.nameOfPlace = nameOfPlace;
    }

    public String getStreetOfPlace() {
        return streetOfPlace;
    }

    public void setStreetOfPlace(String streetOfPlace) {
        this.streetOfPlace = streetOfPlace;
    }

    public String getCityOfLocation() {
        return cityOfLocation;
    }

    public void setCityOfLocation(String cityOfLocation) {
        this.cityOfLocation = cityOfLocation;
    }

    public String getCountryOfLocation() {
        return countryOfLocation;
    }

    public void setCountryOfLocation(String countryOfLocation) {
        this.countryOfLocation = countryOfLocation;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", idLocation);
            json.put("altitude", altitude);
            json.put("longitude", longitude);
            json.put("nameOfPlace", nameOfPlace);
            json.put("streetOfPlace", streetOfPlace);
            json.put("cityOfLocation", cityOfLocation);
            json.put("countryOfLocation", countryOfLocation);
            json.put("zipCode", zipCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
