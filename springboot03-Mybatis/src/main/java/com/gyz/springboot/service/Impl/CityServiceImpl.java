package com.gyz.springboot.service.Impl;

import com.gyz.springboot.mapper.CityMapper;
import com.gyz.springboot.pojo.City;
import com.gyz.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    @Override
    public City findByName(String cityName) {
        return cityMapper.findByName(cityName);
    }
}
