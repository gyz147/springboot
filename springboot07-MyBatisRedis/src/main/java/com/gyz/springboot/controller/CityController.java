package com.gyz.springboot.controller;

import com.gyz.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 控制层
 */
@Controller
@RequestMapping(value = "/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 查询所有城市
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(ModelMap map){
        map.addAttribute("cityList",cityService.findAll());
        return "cityList";
    }

    /**
     * 通过Id查找城市
     * @param id
     * @return
     */
    @RequestMapping(value = "/query/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable Long id, ModelMap map){
        map.addAttribute("city",cityService.findById(id));
        map.addAttribute("action","update");
        return "cityForm";
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteById(@PathVariable Long id){

        return "cityList";
    }
}
