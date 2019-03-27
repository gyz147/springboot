package com.gyz.springboot.mapper;

import com.gyz.springboot.pojo.City;
import org.apache.ibatis.annotations.*;

@Mapper
//@Component(value = "cityMapper")
public interface CityMapper {
    @Select("select * from t_city where city_name = #{cityName}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "provinceId", column = "province_id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "description", column = "description"),
    })
    City findByName(@Param("cityName") String cityName);
}
