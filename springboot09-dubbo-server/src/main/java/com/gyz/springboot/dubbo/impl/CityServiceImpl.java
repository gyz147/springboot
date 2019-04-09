package com.gyz.springboot.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gyz.springboot.domain.City;
import com.gyz.springboot.dubbo.CityService;

/**
 * 城市业务 Dubbo 服务层实现层
 *
 */
// 注册为 Dubbo 服务

@Service(version = "1.0.0")
public class CityServiceImpl implements CityService {

    public City findCityByName(String cityName) {
        return new City(1L,2L,"淮安","是一座美丽的城市");
    }
}
