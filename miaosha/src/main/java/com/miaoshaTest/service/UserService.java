package com.miaoshaTest.service;

import com.miaoshaTest.error.BusinessException;
import com.miaoshaTest.service.model.UserModel;

/**
 * Created by DELL on 2019/9/10.
 */
public interface UserService {
    //通过用户ID获取用户对象的方法
    UserModel getUserById(Integer id);

    //创建用户对象
    void register(UserModel userModel) throws BusinessException;

    /*
    * telphone:用户注册手机
    * password:用户加密后的密码
    * */
    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
