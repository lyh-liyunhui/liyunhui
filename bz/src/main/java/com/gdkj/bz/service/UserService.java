package com.gdkj.bz.service;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.UserDO;
import com.gdkj.bz.error.BusinessException;

import java.util.List;

/**
 * Created by DELL on 2019/11/12.
 */
public interface UserService {

    /*
     * 用户登录
     * telphone 电话号码
     * password 密码
     * */
    public UserVO login(String telphone, String password);

    public UserDO select(Integer id) throws BusinessException;
    /*
    *
    *用户注册
    *前端实体类 UserVO
    * */
    public void  register(UserVO userVO) throws BusinessException;

    public List<GoodsDO>getallgoods(Integer userId) throws BusinessException;

    public List<OrderListVO>getallorder(Integer userId) throws BusinessException;

    public void changeuser(UserDO userDO) throws BusinessException;

    List<UserVO> Getalluser();

    void deluser(Integer id) throws BusinessException;
}
