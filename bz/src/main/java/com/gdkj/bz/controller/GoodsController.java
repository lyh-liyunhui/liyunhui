package com.gdkj.bz.controller;

import com.gdkj.bz.controller.VO.GoodsCartVO;
import com.gdkj.bz.controller.VO.GoodsVO;
import com.gdkj.bz.controller.VO.GoodsWordsVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.GoodsMessageDO;
import com.gdkj.bz.entity.UserDO;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.error.EmBusinessError;
import com.gdkj.bz.response.CommonReturnType;
import com.gdkj.bz.service.GoodsService;
import com.gdkj.bz.util.GoodsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DELL on 2019/11/15.
 */
@Controller
@RequestMapping("/goods")
@CrossOrigin(allowCredentials ="true",allowedHeaders ="*")
public class GoodsController {

    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private GoodsUtil goodsUtil;
    @Autowired
    private GoodsService goodsService;
    /*
    * 添加商品
    * goodsname 商品名称 level 商品成色
     * remark  商品的详细信息 price 商品价格
     * sort 商品的分类 image 图片
    * */
    @RequestMapping(value = "/addgoods")
    @ResponseBody
    @RequiresRoles("user")
    public CommonReturnType addGoods(@RequestParam("goodsname") String goodsname,
                                     @RequestParam("level") Integer level,
                                     @RequestParam("remark") String remark,
                                     @RequestParam("price") BigDecimal price,
                                     @RequestParam("sort") Integer sort,
                                     @RequestParam(value = "file",required = false) MultipartFile file) throws BusinessException {
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        /*
        * 封装实体类
        * */
        String image=goodsUtil.fileUpload(file);

        GoodsVO goodsVO=goodsUtil.addoneGoods(goodsname,level,remark,price,sort,image,userVO);

        goodsService.addoneGoods(goodsVO);

        return CommonReturnType.create(null);
    }


    /*
    * 删除一个商品
    * */
    @RequestMapping(value = "/removegoods")
    @ResponseBody
    public CommonReturnType removeGoods(@RequestParam("id") Integer id) throws BusinessException {
        GoodsVO goodsVO=goodsUtil.removeGoods(id);

        goodsService.removeGoods(goodsVO);

        return CommonReturnType.create(null);
    }
    /*
    * 查询后台单个商品
    * */
    @RequestMapping(value = "selectgoods")
    public String selectgoods(@RequestParam("id") Integer id ,Model model) throws BusinessException {

       GoodsDO goodsDO= goodsService.selecthtGoods(id);

        model.addAttribute("goods",goodsDO);

        return  CommonReturnType.success("ht/gin");
    }
    /*
    * 修改商品信息
    * */
    @RequestMapping("updategoods")
    @ResponseBody
    public CommonReturnType updategoods(
                @RequestParam("id") Integer id,
                @RequestParam("goodsname") String goodsname,
                @RequestParam("level") Integer level,
                @RequestParam("remark") String remark,
                @RequestParam("price") BigDecimal price) throws BusinessException {


        GoodsDO goodsDO=new GoodsDO();
        goodsDO.setId(id);
        goodsDO.setGoodsname(goodsname);
        goodsDO.setLevel(level);
        goodsDO.setRemark(remark);
        goodsDO.setPrice(price);

        goodsService.updateGoods(goodsDO);

       return CommonReturnType.create(null);
        }


    /*
    * 查询单个商品
    * */
    @RequestMapping(value = "/selectonegoods")
    public String selectoneGoods(@RequestParam("id") Integer id, Model model) throws BusinessException {

        GoodsVO goodsVO=goodsUtil.selectoneGoods(id);
        //查询单个商品id
        GoodsVO goodsVO1=goodsService.selectoneGoods(goodsVO);

        List<GoodsMessageDO> goodsMessageDOList=goodsService.selectoneGoodsmessage(goodsVO.getId());


        if(goodsMessageDOList!=null){
            List<UserDO>userDOList=goodsMessageDOList.stream().map(goodsMessageDO -> {
                UserDO userDO=goodsService.selectgoodsuser(goodsMessageDO.getUserId());
                return userDO;
                }).collect(Collectors.toList());
            List<GoodsWordsVO> goodsWordsVOList=goodsUtil.convertFromentity7(goodsMessageDOList,userDOList);

            model.addAttribute("goodswords",goodsWordsVOList);

           /* model.addAttribute("userDOList",userDOList);
            model.addAttribute("goodsMessageDOList",goodsMessageDOList);*/
            UserDO userDO1=new UserDO();
            userDO1.setUsername(null);
            GoodsMessageDO goodsMessageDO1=new GoodsMessageDO();
            goodsMessageDO1.setCreatetime(null);
            goodsMessageDO1.setContent(null);
            model.addAttribute("user",userDO1);
            model.addAttribute("goodsmsg",goodsMessageDO1);
        }else{
            UserDO userDO1=new UserDO();
            userDO1.setUsername(null);
            GoodsMessageDO goodsMessageDO1=new GoodsMessageDO();
            goodsMessageDO1.setCreatetime(null);
            goodsMessageDO1.setContent(null);
            model.addAttribute("user",userDO1);
            model.addAttribute("goodsmsg",goodsMessageDO1);
        }
        model.addAttribute("goodsVO",goodsVO1);

        httpServletRequest.getSession().setAttribute("goods",goodsVO1);

        return CommonReturnType.success("goodsdetails");
    }


    /*
    * 分页查询所有商品
    * */
    @RequestMapping(value = "/getAllGoods")
    public String selectAllGoods(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum, Model model){

        PageHelper.startPage(pageNum,8);
        List<GoodsDO> goodsDOList=goodsService.selectallgoods();

        PageInfo<GoodsDO> pageInfo=new PageInfo<GoodsDO>(goodsDOList);


        model.addAttribute("pageInfo",pageInfo);
        return CommonReturnType.success("index");
    }

    /*
    * 查询单个分类的所有商品
    * */
    @RequestMapping(value = "/getsortGoods")
    public String selectsortGoods(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam("id") Integer id, Model model){

        PageHelper.startPage(pageNum,8);
        List<GoodsDO> goodsDOList=goodsService.selectsortgoods(id);

        PageInfo<GoodsDO> pageInfo=new PageInfo<GoodsDO>(goodsDOList);


        model.addAttribute("pageInfo",pageInfo);

        return CommonReturnType.success("index");
    }

    /*
    * 模糊查询所有商品
    * */
    @RequestMapping(value = "/getsearchGoods")
    public String selectSearchGoods(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum,@RequestParam("goodsname") String goodsname, Model model){
        PageHelper.startPage(pageNum,8);
        List<GoodsDO> goodsDOList=goodsService.selectsSearchGoods(goodsname);

        PageInfo<GoodsDO> pageInfo=new PageInfo<GoodsDO>(goodsDOList);


        model.addAttribute("pageInfo",pageInfo);

        return CommonReturnType.success("index");
    }

    /*
    * 添加商品留言
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/addwords")
    @ResponseBody
    public CommonReturnType addwords(@RequestParam("content")String content) throws BusinessException {

        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");
        GoodsVO goodsVO= (GoodsVO) httpServletRequest.getSession().getAttribute("goods");

        GoodsWordsVO goodsWordsVO=goodsUtil.addwords(userVO.getId(),goodsVO.getId(),content);

        GoodsWordsVO goodsWordsVO1=goodsService.addwords(goodsWordsVO);

        goodsWordsVO1.setUsername(userVO.getUsername());

        return CommonReturnType.create(goodsWordsVO1);
    }


    /*
    * 商品添加到购物车
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/addcart")
    @ResponseBody
    public CommonReturnType addcart(@RequestParam("id")Integer id,@RequestParam(defaultValue = "1",value = "amount")Integer amount) throws BusinessException {
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        goodsService.addcart(id,amount,userVO.getId());
        return CommonReturnType.create(null);
    }

    /*
    * 查看购物车商品
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/getAllcart")
    public String getAllcart() throws BusinessException {
        //从session中获取userid
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");
        if(userVO==null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN);
        }
        List<GoodsCartVO>goodsCartVOList=goodsService.selectcart(userVO.getId());

        httpServletRequest.getSession().setAttribute("goodsCart",goodsCartVOList);

        return CommonReturnType.success("shoppingcart");
    }

    /*
    * 删除购物车中的商品
    * */
    @RequestMapping(value = "/delgoods")
    @ResponseBody
    public CommonReturnType delgoods(@RequestParam("goodsId")Integer goodsId){

        goodsService.delgoodscart(goodsId);
        return CommonReturnType.create(null);
    }

    /*
    * 获取后台商品列表
    * */
    @RequiresRoles("admin")
    @RequestMapping(value = "/gethtgoods")
    public String gethtgoods(Model model){

        List<GoodsDO>goodsDOList=goodsService.selectallgoods();

        model.addAttribute("goodslist",goodsDOList);

        return CommonReturnType.success("ht/goodslist");
    }
}
