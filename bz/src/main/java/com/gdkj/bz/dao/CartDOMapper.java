package com.gdkj.bz.dao;

import com.gdkj.bz.entity.CartDO;

import java.util.List;

public interface CartDOMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteBygoodsId(Integer goodsId);

    int insert(CartDO record);

    int insertSelective(CartDO record);

    CartDO selectByPrimaryKey(Integer id);

    List<CartDO> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(CartDO record);

    int updateByPrimaryKey(CartDO record);
}