package com.gyz.springboot.service.Impl;

import com.gyz.springboot.pojo.User;
import com.gyz.springboot.pojo.UserRepository;
import com.gyz.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        LOGGER.info("查找所有用户：");
        return userRepository.findAll();
    }

    @Override
    public User insertByUser(User user) {
        LOGGER.info("新增用户：");
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info("修改用户：");
        return userRepository.save(user);
    }

    @Override
    public User delete(Long id) {
        User user = userRepository.findById(id).get();
        LOGGER.info("删除用户：");
        userRepository.delete(user);
        return user;
    }

    @Override
    public User findById(Long id) {
        LOGGER.info("查找用户：");
        return userRepository.findById(id).get();
    }
}
