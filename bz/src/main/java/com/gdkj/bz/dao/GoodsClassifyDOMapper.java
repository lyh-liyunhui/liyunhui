package com.gdkj.bz.dao;

import com.gdkj.bz.entity.GoodsClassifyDO;

public interface GoodsClassifyDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsClassifyDO record);

    int insertSelective(GoodsClassifyDO record);

    GoodsClassifyDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsClassifyDO record);

    int updateByPrimaryKey(GoodsClassifyDO record);
}