package com.gdkj.bz.dao;

import com.gdkj.bz.entity.GoodsMessageDO;

import java.util.List;

public interface GoodsMessageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsMessageDO record);

    int insertSelective(GoodsMessageDO record);

    GoodsMessageDO selectByPrimaryKey(Integer id);

    List<GoodsMessageDO> selectByGoodsid(Integer goodsId);

    int updateByPrimaryKeySelective(GoodsMessageDO record);

    int updateByPrimaryKey(GoodsMessageDO record);
}