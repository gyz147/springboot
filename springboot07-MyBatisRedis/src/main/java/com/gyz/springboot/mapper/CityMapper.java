package com.gyz.springboot.mapper;

import com.gyz.springboot.pojo.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Dao层
 */
@Mapper
public interface CityMapper {
    /**
     * 查找所有城市
     *
     * @return
     */
    @Select("select * from t_city")
    @Results(id = "City", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "cityName", column = "city_name"),
            @Result(property = "desc", column = "description")
    })
    public List<City> findAll();

    /**
     * 通过Id查找城市
     *
     * @param id
     * @return
     */
    @Select("select * from t_city where id = #{id}")
    @ResultMap("City")
    public City findById(@Param(value = "id") Long id);

    /**
     * 通过Id删除城市
     *
     * @param id
     */
    @Delete("delete from t_city where id = #{id}")
    public void deleteById(@Param(value = "id") Long id);
}
