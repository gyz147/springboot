package com.gyz.springboot.service;

import com.gyz.springboot.pojo.City;

public interface CityService {
    /**
     * 根据城市名称查询城市
     * @param name
     * @return
     */
    City findByName(String name);
}
