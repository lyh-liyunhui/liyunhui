package com.gdkj.bz.service.impl;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.dao.*;
import com.gdkj.bz.entity.*;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.error.EmBusinessError;
import com.gdkj.bz.service.UserService;
import com.gdkj.bz.util.OrderUtil;
import com.gdkj.bz.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2019/11/12.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserUtil userUtil;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;
    @Autowired
    private GoodsDOMapper goodsDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private OrderUtil orderUtil;
    @Autowired
    private RoleDOMapper roleDOMapper;
    @Autowired
    private PermissionDOMapper permissionDOMapper;

    /*
    *
    * 用户登录
    * 电话号码 telphone 密码password
    * */
    @Override
    public UserVO login(String telphone, String password) {

        UserDO userDO=userDOMapper.selectByTelphone(telphone);


        UserPasswordDO userPasswordDO=userPasswordDOMapper.selectByUserId(userDO.getId());
        UserVO userVO=userUtil.convertFromentity(userDO,userPasswordDO);

        if(!StringUtils.equals(password,userVO.getPassword())){
            System.out.println("输入的密码不一致");
        }
        return roleAndpermission(userVO);
    }

    @Override
    public UserDO select(Integer id) throws BusinessException {

        if(id==null){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"id不存在");

        }
        UserDO userDO=userDOMapper.selectByPrimaryKey(id);

        return userDO;
    }

    /*
    * 根据用户信息获取角色和权限
    * */
    public  UserVO roleAndpermission(UserVO userVO) {
        List<RoleDO> roleDOs=roleDOMapper.selectByUserId(userVO.getId());

        userVO.setRole(roleDOs);
        userVO.setPermissions(permissionDOMapper.selectByPerId(roleDOs.get(0).getPerId()));
        return userVO;
    }
    /*
    * 用户注册
    *
    * */
    @Override
    @Transactional
    public void register(UserVO userVO) throws BusinessException {
        if(userVO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserDO userDO=userDOMapper.selectByTelphone(userVO.getTelphone());
        if(userDO!=null) {
            throw new BusinessException(EmBusinessError.USER_EXIST);
        }
        UserDO userDO1=userUtil.convertFromentity1(userVO);
        userDOMapper.insertSelective(userDO1);

        userVO.setId(userDO1.getId());
        UserPasswordDO userPasswordDO=userUtil.convertPasswordFromModel(userVO);

        userPasswordDOMapper.insertSelective(userPasswordDO);

        RoleDO roleDO=new RoleDO();
        roleDO.setPerId(2);
        roleDO.setUserId(userDO1.getId());
        roleDO.setRole("user");
        roleDOMapper.insertSelective(roleDO);
    }

    /*
    * 查询用户发布的商品
    * */
    @Override
    public List<GoodsDO> getallgoods(Integer userId) throws BusinessException {
        if(userId==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<GoodsDO>goodsDOList=goodsDOMapper.selectByuserId(userId);

        return goodsDOList;
    }

    @Override
    public List<OrderListVO> getallorder(Integer userId) throws BusinessException {
        if(userId==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        List<OrderListVO>orderListVOs=new ArrayList<>();
        List<OrderDO>orderDOList=orderDOMapper.selectByUserId(userId);
        for (OrderDO orderDO:orderDOList){
            GoodsDO goodsDO=goodsDOMapper.selectByPrimaryKey(orderDO.getGoodsId());

            OrderListVO orderListVO=orderUtil.convertFromentity2(goodsDO,orderDO);

            orderListVOs.add(orderListVO);
        }
        return orderListVOs;
    }

    /*
    * 修改用户个心信息
    * */
    @Override
    public void changeuser(UserDO userDO) throws BusinessException {
        if(userDO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST,"用户不存在");
        }
        userDOMapper.updateByPrimaryKeySelective(userDO);

    }

    @Override
    public List<UserVO> Getalluser() {
        List<UserDO>userDOList=userDOMapper.selectalluser();

        List<UserVO>userVOList=userDOList.stream().map(userDO -> {
            UserVO userVO = userUtil.convertFromentity2(userDO);
            return userVO;
        }).collect(Collectors.toList());

            return userVOList;
    }

    @Override
    @Transactional
    public void deluser(Integer id) throws BusinessException {
        if(id==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户id不存在");
        }

        List<GoodsDO> goodsDOList=goodsDOMapper.selectByuserId(id);
        for (GoodsDO goodsDO:goodsDOList){

            orderDOMapper.deleteByGoodsId(goodsDO.getId());
            goodsDOMapper.deleteByPrimaryKey(goodsDO.getId());
        }
        userDOMapper.deleteByPrimaryKey(id);
    }
}
