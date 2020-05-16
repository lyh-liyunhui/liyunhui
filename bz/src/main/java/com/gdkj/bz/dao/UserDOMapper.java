package com.gdkj.bz.dao;

import com.gdkj.bz.entity.UserDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    UserDO selectByTelphone(String telphone);

    List<UserDO>selectalluser();

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}