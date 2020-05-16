package com.gdkj.bz.util;

import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.UserDO;
import com.gdkj.bz.entity.UserPasswordDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * Created by DELL on 2019/11/12.
 */
@Component
public class UserUtil {

    /*
    *
    * 组装实体类
    * 组装UserPassword
    * */
    public UserPasswordDO convertPasswordFromModel(UserVO userVO){
        if(userVO==null){
            return null;
        }
        UserPasswordDO userPasswordDO=new UserPasswordDO();
        userPasswordDO.setPassword(userVO.getPassword());
        userPasswordDO.setUserId(userVO.getId());
        return userPasswordDO;
    }
    /*
    * 组装实体类
    * 组装UserDO
    * */
    public UserDO convertFromentity1(UserVO userVO){
        if(userVO==null){
            return null;
        }
        UserDO userDO=new UserDO();
        BeanUtils.copyProperties(userVO,userDO);
        return userDO;
    }
    public UserVO convertFromentity2(UserDO userDO){
        if(userDO==null){
            return null;
        }
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        return userVO;
    }
    /*
    * 用户注册流程
    * username 用户名
    * realname 真实姓名 telphone 电话号码 clazz班级  sno学号  dormitory宿舍号
    * gender性别
    * */
    public UserVO registerUser(String username,String realname, String telphone,String password, String clazz, Integer sno, String dormitory, Integer gender) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        UserVO userVO=new UserVO();
        userVO.setUsername(username);
        userVO.setRealname(realname);
        userVO.setTelphone(telphone);
        userVO.setClazz(clazz);
        userVO.setSno(sno);
        userVO.setDormitory(dormitory);
        userVO.setGender(new Byte(String.valueOf(gender.intValue())));
        userVO.setCreatetime(new Date());
        userVO.setPassword(this.EncodeByMd5(password));
        return userVO;
    }

    /*
    * MD5加密方法
    * */
    public String EncodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder=new BASE64Encoder();
        //加密字符串
        String newstr=base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }

    /*
    * 随机生成验证码方法
    * */
    public int getotp(){
        Random random=new Random();
        int randomInt=random.nextInt(99999);
        randomInt+=10000;
        return randomInt;
    }

    /*
    * 组合前端实体类 UserVO
    * 用户实体类 UserDO 密码实体类 UserPasswordDO
    * */
    public UserVO convertFromentity(UserDO userDO, UserPasswordDO userPasswordDO){
        if(userDO==null){
            return null;
        }
        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        if(userPasswordDO !=null){
            userVO.setPassword(userPasswordDO.getPassword());
        }

        return userVO;
    }

}
