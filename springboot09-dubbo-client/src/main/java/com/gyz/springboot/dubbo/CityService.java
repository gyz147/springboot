package com.gyz.springboot.dubbo;

import com.gyz.springboot.domain.City;

/**
 * 城市业务 Dubbo 服务层
 *
 */
public interface CityService {

    /**
     * 根据城市名称，查询城市信息
     * @param cityName
     */
    City findCityByName(String cityName);
}
