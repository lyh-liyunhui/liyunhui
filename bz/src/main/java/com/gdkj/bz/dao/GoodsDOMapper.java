package com.gdkj.bz.dao;

import com.gdkj.bz.entity.GoodsDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsDO record);

    int insertSelective(GoodsDO record);

    GoodsDO selectByPrimaryKey(Integer id);
    List<GoodsDO>selectByuserId(Integer userId);

    List<GoodsDO>selectListGoods();

    List<GoodsDO>selectSortGoods(Integer id);

    List<GoodsDO>selectsSearchGoods(@Param(value = "goodsname") String goodsname);

    List<GoodsDO>selectByCartGoodsId(Integer id);

    int updateByPrimaryKeySelective(GoodsDO record);

    int updateByPrimaryKey(GoodsDO record);
}