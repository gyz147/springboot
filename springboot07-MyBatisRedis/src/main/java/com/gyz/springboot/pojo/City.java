package com.gyz.springboot.pojo;

import java.io.Serializable;

/**
 * 城市实体类
 */
public class City implements Serializable {

    private static final long serialVersionUID = -8366929034564774130L;
    /**
     * 主键id
     */
    private int id;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 城市描述
     */
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
