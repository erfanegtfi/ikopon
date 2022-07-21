package com.ikopon.ikopon.domain.entities;

import java.util.ArrayList;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class City implements KeyValueInterface {

    private String name;

    private String citySlug;

    private String tell;

    private String weblink;

    private String address;

    private ArrayList<Double> location;

    @Override
    public String toString() {
        return
                "City{" +
                        "name = '" + name + '\'' +
                        ",city_slug = '" + citySlug + '\'' +
                        "}";
    }

    public City() {
    }

    public City(String name, String citySlug, String tell, String weblink, String address, ArrayList<Double> location) {
        this.name = name;
        this.citySlug = citySlug;
        this.tell = tell;
        this.weblink = weblink;
        this.address = address;
        this.location = location;
    }

    @Override
    public String getKey() {
        return citySlug;
    }

    @Override
    public String getValue() {
        return name;
    }

    @Override
    public void setKey(String key) {

    }

    @Override
    public void setValue(String value) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCitySlug() {
        return citySlug;
    }

    public void setCitySlug(String citySlug) {
        this.citySlug = citySlug;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Double> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Double> location) {
        this.location = location;
    }
}