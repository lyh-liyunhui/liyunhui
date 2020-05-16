package com.gdkj.bz.service.impl;

import com.gdkj.bz.controller.VO.GoodsCartVO;
import com.gdkj.bz.controller.VO.GoodsVO;
import com.gdkj.bz.controller.VO.GoodsWordsVO;
import com.gdkj.bz.dao.*;
import com.gdkj.bz.entity.*;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.error.EmBusinessError;
import com.gdkj.bz.service.GoodsService;
import com.gdkj.bz.util.GoodsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2019/11/15.
 */
@Service
public class GoodsServiceImpl implements GoodsService {


    @Autowired
    private GoodsUtil goodsUtil;
    @Autowired
    private GoodsDOMapper goodsDOMapper;
    @Autowired
    private GoodsMessageDOMapper goodsMessageDOMapper;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private CartDOMapper cartDOMapper;
    @Autowired
    private OrderDOMapper orderDOMapper;
    /*
    * 添加单个商品
    * */
    @Override
    @Transactional
    public void addoneGoods(GoodsVO goodsVO) throws BusinessException {
        if(goodsVO==null){
        throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        GoodsDO goodsDO=goodsUtil.convertFromentity1(goodsVO);
        goodsDOMapper.insertSelective(goodsDO);

    }

    @Override
    /*
    * 删除单个商品
    * */
    public void removeGoods(GoodsVO goodsVO) throws BusinessException {
        if(goodsVO==null){
        throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        GoodsDO goodsDO=goodsUtil.convertFromentity3(goodsVO);

        goodsDOMapper.deleteByPrimaryKey(goodsDO.getId());

        List<OrderDO>orderDOList=orderDOMapper.selectByGoodsId(goodsDO.getId());
        for (OrderDO orderDO:orderDOList){

            orderDOMapper.deleteByPrimaryKey(orderDO.getId());
        }
    }

    /*
    * 查询单一商品
    * */
    @Override
    public GoodsVO selectoneGoods(GoodsVO goodsVO) throws BusinessException {
        if(goodsVO==null){
            throw  new  BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        //获取商品的id
        GoodsDO goodsDO=goodsUtil.convertFromentity3(goodsVO);

        GoodsDO goodsDO1=goodsDOMapper.selectByPrimaryKey(goodsDO.getId());


        GoodsVO goodsVO1=goodsUtil.convertFromentity4(goodsDO1);

        return goodsVO1;
    }

    @Override
    public GoodsDO selecthtGoods(Integer id) throws BusinessException {
        if(id==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品ID不存在");
        }
        GoodsDO goodsDO=goodsDOMapper.selectByPrimaryKey(id);

        return goodsDO;
    }

    @Override
    public void updateGoods(GoodsDO goodsDO) throws BusinessException {
        if(goodsDO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }
        goodsDOMapper.updateByPrimaryKeySelective(goodsDO);
    }

    /*
    * 分页查询所有商品
    * */
    @Override
    public List<GoodsDO> selectallgoods() {

        List<GoodsDO> goodsDOList=goodsDOMapper.selectListGoods();

        //流式写法，stream API
        //List<GoodsVO>goodsVOList= goodsDOList.stream().map(goodsDO -> {
        //
        //GoodsVO goodsVO=goodsUtil.convertFromentity5(goodsDO);
        //return goodsVO;
        //}).collect(Collectors.toList());

        return goodsDOList;
    }

    /*
    * 查询一类商品
    * */
    @Override
    public List<GoodsDO> selectsortgoods(Integer id) {

        List<GoodsDO> goodsDOList=goodsDOMapper.selectSortGoods(id);

        return goodsDOList;
    }

    /*
    * 模糊搜索商品
    * */
    @Override
    public List<GoodsDO> selectsSearchGoods(String goodsname) {

        List<GoodsDO> goodsDOList=goodsDOMapper.selectsSearchGoods(goodsname);

        return goodsDOList;
    }

    @Override
    /*
    * 根据商品id查询留言
    * */
    public List<GoodsMessageDO> selectoneGoodsmessage(Integer goodsId) {

        List<GoodsMessageDO>goodsMessageDOList=goodsMessageDOMapper.selectByGoodsid(goodsId);

        return goodsMessageDOList;
    }

    /*
    * 根据商品查询用户
    * */
    @Override
    public UserDO selectgoodsuser(Integer userId) {
        UserDO userDO=userDOMapper.selectByPrimaryKey(userId);

        return userDO;
    }

    /*
    * 根据商品id添加商品留言
    * */
    @Override
    public GoodsWordsVO addwords(GoodsWordsVO goodsWordsVO) throws BusinessException {
        if(goodsWordsVO==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        GoodsMessageDO goodsMessageDO=goodsUtil.convertFromentity6(goodsWordsVO);

        goodsMessageDOMapper.insertSelective(goodsMessageDO);

        GoodsMessageDO goodsMessageDO1=goodsMessageDOMapper.selectByPrimaryKey(goodsMessageDO.getId());

        GoodsWordsVO goodsWordsVO1=goodsUtil.convertFromentity7(goodsMessageDO1);

        return goodsWordsVO1;
    }


    /*
    * 添加商品到购物车
    * */
    @Override
    public void addcart(Integer goodsId, Integer amount, Integer userId) throws BusinessException {
        if(goodsId==null&&amount==null&&userId==null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        CartDO cartDO=goodsUtil.addcart(goodsId,amount,userId);
        cartDOMapper.insertSelective(cartDO);
    }

    @Override
    public List<GoodsCartVO> selectcart(Integer userId) {
        List<CartDO>cartDOList=cartDOMapper.selectByUserId(userId);

        List<GoodsCartVO>goodsCartVOList=cartDOList.stream().map(cartDO -> {
            GoodsCartVO goodsCartVO=new GoodsCartVO();

            goodsCartVO.setAmount(cartDO.getAmount());
            List<GoodsDO>goodsDOList=goodsDOMapper.selectByCartGoodsId(cartDO.getGoodsId());
            for (GoodsDO goodsDO:goodsDOList){
                goodsCartVO.setGoodsname(goodsDO.getGoodsname());
                goodsCartVO.setImage(goodsDO.getImage());
                goodsCartVO.setPrice(goodsDO.getPrice());
                goodsCartVO.setGoodsId(goodsDO.getId());
            }
            return goodsCartVO;
        }).collect(Collectors.toList());

        return goodsCartVOList;
    }

    /*
    * 根据商品id删除购物车表中商品
    * */
    @Override
    public void delgoodscart(Integer goodsId) {

        cartDOMapper.deleteBygoodsId(goodsId);
    }

    /*
    * 根据id删除后台商品
    * */
    @Override
    public void removehtGoods(Integer id) {

        goodsDOMapper.deleteByPrimaryKey(id);
    }


}
