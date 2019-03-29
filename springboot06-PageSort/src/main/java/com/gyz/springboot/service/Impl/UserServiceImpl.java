package com.gyz.springboot.service.Impl;

import com.gyz.springboot.pojo.User;
import com.gyz.springboot.pojo.UserRepository;
import com.gyz.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User insertByUser(User user) {
        return userRepository.save(user);
    }
}
