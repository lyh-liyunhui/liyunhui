package com.gdkj.bz.dao;

import com.gdkj.bz.entity.RoleDO;

import java.util.List;

public interface RoleDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDO record);

    int insertSelective(RoleDO record);

    RoleDO selectByPrimaryKey(Integer id);

    List<RoleDO> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(RoleDO record);

    int updateByPrimaryKey(RoleDO record);
}