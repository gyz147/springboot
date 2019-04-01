package com.gyz.springboot.controller;

import com.gyz.springboot.pojo.User;
import com.gyz.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Pageable pageable, ModelMap map) {
        Page<User> page = page = userService.findAll(pageable);
        map.addAttribute("userList", page.getContent());
        map.addAttribute("total", page.getTotalElements());
        map.addAttribute("pageNumber", page.getPageable().getPageNumber());
        return "userList";
    }

    /**
     * 显示创建用户表单
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUserForm(ModelMap map) {
        map.addAttribute("user", new User());
        map.addAttribute("action", "create");
        return "userForm";
    }

    /**
     * 创建用户
     * 处理 "/users" 的 POST 请求，用来获取用户列表
     * 通过 @ModelAttribute 绑定参数，也通过 @RequestParam 从页面中传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postUser(ModelMap map,
                           @ModelAttribute @Valid User user,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("action", "create");
            return "userForm";
        }

        userService.insertByUser(user);

        return "redirect:/users/";
    }

    /**
     * 显示需要更新用户表单
     * 处理 "/users/{id}" 的 GET 请求，通过 URL 中的 id 值获取 User 信息
     * URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(ModelMap map, @PathVariable Long id) {
        map.addAttribute("user", userService.findById(id));
        map.addAttribute("action", "update");
        return "userForm";
    }

    /**
     * 处理 "/users/{id}" 的 PUT 请求，用来更新 User 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putUser(ModelMap map,
                          @ModelAttribute @Valid User user,
                          BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("action", "update");
            return "userForm";
        }

        userService.updateByUser(user);
        return "redirect:/users/";
    }

    /**
     * 处理 "/users/{id}" 的 GET 请求，用来删除 User 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/users/";
    }
}
