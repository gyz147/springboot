package com.gyz.springboot.service;

import com.gyz.springboot.pojo.City;

import java.util.List;

/**
 * service层接口
 */
public interface CityService {
    public List<City> findAll();
    public City findById(Long id);
    public void deleteById(Long id);
}
