package com.gyz.springboot.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gyz.springboot.domain.City;
import org.springframework.stereotype.Component;

/**
 * 城市 Dubbo 服务消费者
 *
 */
@Component
public class CityDubboConsumerService {

    @Reference(version = "1.0.0")
    CityService cityService;

    public void printCity() {
        String cityName="淮安";
        City city = cityService.findCityByName(cityName);
        System.out.println(city.toString());
    }
}
