package com.ikopon.ikopon.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.ikopon.ikopon.data.dataSource.local.room.dataTypeConverter.CityLocationConverter;

import java.util.ArrayList;

import kotlinx.parcelize.Parcelize;

@Entity(tableName = City.TABLE_NAME)
@TypeConverters({CityLocationConverter.class})
@Parcelize
public class City implements KeyValueInterface {
    public final static String TABLE_NAME = "city_list";

    @SerializedName("name")
    private String name;

    @NonNull
    @PrimaryKey()
    @SerializedName("city_slug")
    private String citySlug;

    @SerializedName("tell")
    private String tell;

    @SerializedName("weblink")
    private String weblink;

    @SerializedName("address")
    private String address;

    @SerializedName("location")
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