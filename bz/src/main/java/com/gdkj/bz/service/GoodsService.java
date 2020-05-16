package com.gdkj.bz.service;

import com.gdkj.bz.controller.VO.GoodsCartVO;
import com.gdkj.bz.controller.VO.GoodsVO;
import com.gdkj.bz.controller.VO.GoodsWordsVO;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.GoodsMessageDO;
import com.gdkj.bz.entity.UserDO;
import com.gdkj.bz.error.BusinessException;

import java.util.List;

/**
 * Created by DELL on 2019/11/15.
 */
public interface GoodsService {

    /*
    * 添加一个商品
    * 前端GoodsVO实体类
    * */
    public void addoneGoods(GoodsVO goodsVO) throws BusinessException;

    /*
    * 删除一个商品
    * 前端GoodsVO实体类
    * */
    public void removeGoods(GoodsVO goodsVO) throws BusinessException;

    /*
    * 查询单个商品
    * */
    public GoodsVO selectoneGoods(GoodsVO goodsVO) throws BusinessException;

    /*
    * 查询后台单个商品
    * */
    public GoodsDO selecthtGoods(Integer id) throws BusinessException;

    /*
    * 修改后台单个商品
    * */
    public void updateGoods(GoodsDO goodsDO)throws BusinessException;

    /*
    * 查询所有商品
    * */
    public List<GoodsDO> selectallgoods();
    /*
    * 查询单个分类的商品
    * */
    public List<GoodsDO>selectsortgoods(Integer id);

    public List<GoodsDO>selectsSearchGoods(String goodsname);

    public List<GoodsMessageDO> selectoneGoodsmessage(Integer goodsId);

    public UserDO selectgoodsuser(Integer userId);

    public GoodsWordsVO addwords(GoodsWordsVO goodsWordsVO) throws BusinessException;

    public void addcart(Integer goodsId, Integer amount, Integer userId) throws BusinessException;

    public List<GoodsCartVO>selectcart(Integer userId);

    public void delgoodscart(Integer goodsId);

    void removehtGoods(Integer id);
}
