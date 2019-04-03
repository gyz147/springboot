package com.gyz.springboot.controller;

import com.gyz.springboot.pojo.City;
import com.gyz.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String findAll(ModelMap map) {
        map.addAttribute("cityList", cityService.findAll());
        return "cityList";
    }

    /**
     * 通过Id查找城市
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable Long id, ModelMap map) {
        map.addAttribute("city", cityService.findById(id));
        map.addAttribute("action", "update");
        return "cityForm";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateCity(@ModelAttribute City city){
        int result = cityService.updateCity(city);
        System.out.println("成功更新" + result + "条数据！");
        return "redirect:/city/";
    }

    /**
     * 通过Id删除城市
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable Long id) {
        int result = cityService.deleteById(id);
        System.out.println("成功删除" + result + "条数据！");
        return "redirect:/city/";
    }

    /**
     * 捕获添加城市事件，跳转添加城市页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCityForm(ModelMap map) {
        map.addAttribute("city", new City());
        map.addAttribute("action", "add");
        return "cityForm";
    }

    /**
     * 添加城市信息
     *
     * @param city
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCity(@ModelAttribute City city) {
        int result = cityService.addCity(city);
        System.out.println("成功添加" + result + "条数据！");
        return "redirect:/city/";
    }


}
