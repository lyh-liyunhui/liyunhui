package com.lyh.service.Impl;

import com.lyh.dao.UserRepository;
import com.lyh.po.User;
import com.lyh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2020/11/26.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username,password);
        return user;
    }
}
