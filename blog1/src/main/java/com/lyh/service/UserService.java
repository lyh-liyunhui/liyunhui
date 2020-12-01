package com.lyh.service;

import com.lyh.po.User;

/**
 * Created by Administrator on 2020/11/26.
 */
public interface UserService {

    User checkUser(String username,String password);
}
