package com.gyz.springboot.service.Impl;

import com.gyz.springboot.mapper.CityMapper;
import com.gyz.springboot.pojo.City;
import com.gyz.springboot.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * service层实现类
 */
@Service
public class CityServiceImpl implements CityService {

    /**
     * 打印日志的切面
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有城市
     * 查询逻辑：
     * 一般全量数据使不会存入缓存的，故直接查数据库，不需要和缓存有任何操作
     *
     * @return
     */
    @Override
    public List<City> findAll() {
        return cityMapper.findAll();
    }

    /**
     * 通过Id查找城市
     * 查询逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存中不存在，从DB中获取并插入缓存
     *
     * @param id
     * @return
     */
    @Override
    public City findById(Long id) {
        String key = "city_" + id;
        City city = null;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        //如果缓存中存在数据
        if (redisTemplate.hasKey(key)) {
            city = operations.get(key);
            LOGGER.info("从Redis缓存中获取数据：" + city.toString());
        } else {
            city = cityMapper.findById(id);
            LOGGER.info("从DB中获取数据：" + city.toString());
//            operations.set(key, city, 10, TimeUnit.SECONDS); //键 值 超时时间 超时时间单位
            operations.set(key, city);
            LOGGER.info("数据插入到缓存中：" + city.toString());
        }
        return city;
    }

    /**
     * 通过Id删除城市
     * 删除逻辑：
     * 如果删除的数据在缓存中存在，则把缓存中的数据也删除
     *
     * @param id
     */
    @Override
    public int deleteById(Long id) {
        String key = "city_" + id;
        int result = cityMapper.deleteById(id);
        LOGGER.info("从DB中删除城市，id：" + id);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            LOGGER.info("从缓存中删除key：" + key);
        }
        return result;
    }

    /**
     * 添加城市
     * 添加逻辑：
     * 直接插入数据库，不用操作缓存，因为后续查询的时候缓存中没有会添加。
     * 用户关心的也只是添加这个动作成功了没，插入DB中直接跟用户说成功就行，
     * 如果额外增加一部添加到缓存会多额外的时间消耗
     *
     * @param city
     * @return
     */
    @Override
    public int addCity(City city) {
        return cityMapper.addCity(city);
    }

    /**
     * 更新城市
     * 更新逻辑：
     * 先更新DB，再删除缓存（如果缓存存在）
     * 不建议先删除缓存再更新DB，在并发的环境下容易产生脏数据
     *
     * @param city
     * @return
     */
    @Override
    public int updateCity(City city) {
        int result = cityMapper.updateCity(city);
        String key = "city_" + city.getId();
        if(redisTemplate.hasKey(key)){
            redisTemplate.delete(key);
            LOGGER.info("从缓存中删除城市:" + city.toString());
        }
        return result;
    }
}
