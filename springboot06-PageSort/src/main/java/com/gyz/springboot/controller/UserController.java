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
        Page<User> page = userService.findAll(pageable);
        map.addAttribute("userList",page.getContent());
        map.addAttribute("total",page.getTotalElements());
        map.addAttribute("page",page.getPageable().getPageNumber()+1);
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
}
