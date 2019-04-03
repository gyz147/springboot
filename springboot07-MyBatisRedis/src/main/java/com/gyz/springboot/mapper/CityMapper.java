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
    public int deleteById(@Param(value = "id") Long id);


    /**
     * 添加城市
     *
     * @param city
     * @return
     */
    @Insert("insert into t_city(city_name,description)values(#{cityName},#{desc})")
    public int addCity(City city);

    /**
     * 通过id更新城市
     *
     * @param city
     * @return
     */
    @Update("update t_city set city_name = #{cityName},description = #{desc} where id = #{id}")
    public int updateCity(City city);
}
