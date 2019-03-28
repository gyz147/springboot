package com.gyz.springboot.service;

import com.gyz.springboot.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User insertByUser(User user);

    User update(User user);

    User delete(Long id);

    User findById(Long id);
}
