package com.gyz.springboot.service;

import com.gyz.springboot.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public Page<User> findAll(Pageable pageable);
    User insertByUser(User user);
}
