package com.gyz.springboot.service;

import com.gyz.springboot.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public Page<User> findAll(Pageable pageable);
    public User insertByUser(User user);
    public User findById(Long id);
    public User updateByUser(User user);
    public void deleteById(Long id);
}
