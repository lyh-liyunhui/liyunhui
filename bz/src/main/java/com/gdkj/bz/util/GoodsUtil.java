package com.gdkj.bz.util;

import com.gdkj.bz.controller.VO.GoodsVO;
import com.gdkj.bz.controller.VO.GoodsWordsVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2019/11/15.
 */
@Component
public class GoodsUtil {
    public static final String path="D:/idea/bz/src/main/resources/static/image/goods_img/";

    /*
* 组装实体类
* 组装List<GoodsVO>实体类
* */
    public GoodsVO convertFromentity5(GoodsDO goodsDO){
        if(goodsDO==null){
            return null;
        }
        GoodsVO goodsVO=new GoodsVO();
        BeanUtils.copyProperties(goodsDO,goodsVO);
        return  goodsVO;
    }
    /*
  * 组装实体类
  * 组装GoodsDO
  * */
    public GoodsVO convertFromentity4(GoodsDO goodsDO){
        if(goodsDO==null){
            return null;
        }
        GoodsVO g=new GoodsVO();
        BeanUtils.copyProperties(goodsDO,g);
        return g;
    }

    /*
   * 组装实体类
   * 组装GoodsDO
   * */
    public GoodsDO convertFromentity1(GoodsVO goodsVO){
        if(goodsVO==null){
            return null;
        }
        GoodsDO goodsDO=new GoodsDO();
        BeanUtils.copyProperties(goodsVO,goodsDO);
        return goodsDO;
    }

     /*
    * 添加单个商品
    * goodsname 商品名称 level 商品成色
     * remark  商品的详细信息 price 商品价格
     * sort 商品的分类 image 图片 creatietime 创建时间 userVO 用户id
    * */
    public GoodsVO addoneGoods(String goodsname, Integer level, String remark, BigDecimal price, Integer sort, String image,UserVO userVO){
        GoodsVO goodsVO=new GoodsVO();
        goodsVO.setGoodsname(goodsname);
        goodsVO.setLevel(level);
        goodsVO.setRemark(remark);
        goodsVO.setPrice(price);
        goodsVO.setSort(sort);
        goodsVO.setImage(image);
        goodsVO.setCreatetime(new Date());
        goodsVO.setUserId(userVO.getId());
        return  goodsVO;
    }

    /*
    * 删除商品
    * 商品 id
    * */
    public GoodsVO removeGoods(Integer id) {
        GoodsVO g=new GoodsVO();
        g.setId(id);
        return g;
    }

    /*
 * 组装实体类
 * 组装GoodsDO id
 * */
    public GoodsDO convertFromentity3(GoodsVO goodsVO){
        if(goodsVO==null){
            return null;
        }
        GoodsDO goodsDO=new GoodsDO();
        goodsDO.setId(goodsVO.getId());
        return goodsDO;
    }

    public GoodsVO selectoneGoods(Integer id) {
        GoodsVO g=new GoodsVO();
        g.setId(id);
        return g;
    }
    /*返回url地址*/
    public String fileUpload(MultipartFile file){
        if(file.isEmpty()){
            System.out.println("文件为空");
        }
        //获取文件名
        String fileName=file.getOriginalFilename();
        //获取截取文件名
        String suffixName=fileName.substring(fileName.lastIndexOf("."));
        //随机生成数字加文件名
        fileName= UUID.randomUUID()+suffixName;
        File dest=new File(path+fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url="http://localhost:8090/image/goods_img/";
        return url+fileName;
    }
    /*
    * 添加商品留言
    * */
    public GoodsWordsVO addwords(Integer userId,Integer goodsId,String content){

        GoodsWordsVO goodsWordsVO=new GoodsWordsVO();
        goodsWordsVO.setUserId(userId);
        goodsWordsVO.setContent(content);
        goodsWordsVO.setGoodsId(goodsId);
        goodsWordsVO.setCreatetime(new Date());
        return goodsWordsVO;
    }
    public GoodsMessageDO convertFromentity6(GoodsWordsVO goodsWordsVO){
        if (goodsWordsVO==null){
            return  null;
        }
        GoodsMessageDO goodsMessageDO=new GoodsMessageDO();
        BeanUtils.copyProperties(goodsWordsVO,goodsMessageDO);
        return goodsMessageDO;
    }

    public GoodsWordsVO convertFromentity7(GoodsMessageDO goodsMessageDO){
        if (goodsMessageDO==null){
            return  null;
        }
        GoodsWordsVO goodsWordsVO=new GoodsWordsVO();
        BeanUtils.copyProperties(goodsMessageDO,goodsWordsVO);
        return goodsWordsVO;
    }
    public List<GoodsWordsVO> convertFromentity7(List<GoodsMessageDO> goodsMessageDOList, List<UserDO> userDOList) {
        List<GoodsWordsVO> goodsWordsVOList = goodsMessageDOList.stream().map(goodsMessageDO -> {
            GoodsWordsVO goodsWordsVO = new GoodsWordsVO();
            goodsWordsVO.setContent(goodsMessageDO.getContent());
            goodsWordsVO.setCreatetime(goodsMessageDO.getCreatetime());
            for (UserDO userDO:userDOList){
                goodsWordsVO.setUsername(userDO.getUsername());
            }
            return goodsWordsVO;
        }).collect(Collectors.toList());
        return goodsWordsVOList;
    }
    public CartDO addcart(Integer goodsId, Integer amount, Integer userId){
        CartDO cartDO=new CartDO();
        cartDO.setUserId(userId);
        cartDO.setGoodsId(goodsId);
        cartDO.setAmount(amount);
        cartDO.setCreatetime(new Date());
        return cartDO;
    }
}
