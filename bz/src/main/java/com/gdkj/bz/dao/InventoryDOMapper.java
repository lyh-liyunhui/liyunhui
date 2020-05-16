package com.gdkj.bz.dao;

import com.gdkj.bz.entity.InventoryDO;
import org.apache.ibatis.annotations.Param;

public interface InventoryDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryDO record);

    int insertSelective(InventoryDO record);

    boolean decreaseCount(@Param("goodsId") Integer goodsId, @Param("amount") Integer amount);

    boolean increaseSales(@Param("goodsId") Integer goodsId, @Param("amount") Integer amount);

    InventoryDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryDO record);

    int updateByPrimaryKey(InventoryDO record);
}