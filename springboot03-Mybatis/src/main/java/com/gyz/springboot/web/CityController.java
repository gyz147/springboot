package com.gyz.springboot.web;

import com.gyz.springboot.pojo.City;
import com.gyz.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    @ResponseBody
    public City findByName(@RequestParam(value = "cityName",required = true) String name) {
        return cityService.findByName(name);
    }
}
