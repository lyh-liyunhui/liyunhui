package com.gdkj.bz.controller;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.controller.VO.UserVO;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.UserDO;
import com.gdkj.bz.error.BusinessException;
import com.gdkj.bz.error.EmBusinessError;
import com.gdkj.bz.response.CommonReturnType;
import com.gdkj.bz.service.UserService;
import com.gdkj.bz.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by DELL on 2019/11/12.
 */
@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials ="true",allowedHeaders ="*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserUtil userUtil;
    @Autowired
    private HttpServletRequest httpServletRequest;
    /*
    * 用户登录
    * 电话号码telphone 密码password
    * */
    @RequestMapping(value = "/login",method ={RequestMethod.POST})
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone, @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(org.apache.commons.lang3.StringUtils.isEmpty(telphone)|| org.apache.commons.lang3.StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(telphone,userUtil.EncodeByMd5(password));
        try {
            subject.login(usernamePasswordToken);

        }catch (AuthenticationException | AuthorizationException e) {
            e.printStackTrace();
        }

        UserVO userVO=(UserVO) SecurityUtils.getSubject().getPrincipal();

        //将用户绑定到session中
        httpServletRequest.getSession().setAttribute("user",userVO);
        return CommonReturnType.create(null);
    }


    /*
    * 用户注册
    * username 用户名
    * realname 真实姓名 telphone 电话号码 clazz班级  sno学号  dormitory宿舍号
    * gender性别
    * */
    @RequestMapping(value = "/register" ,method = {RequestMethod.POST})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name ="username")String username,
                           @RequestParam(name ="realname")String realname,
                           @RequestParam(name ="telphone") String telphone,
                           @RequestParam(name ="otpCode")String otpCode,
                           @RequestParam(name ="password") String password,
                           @RequestParam(name ="clazz")String clazz,
                           @RequestParam(name ="sno")Integer sno,
                           @RequestParam(name ="dormitory")String dormitory,
                           @RequestParam(name ="gender")Integer gender) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的OTPcode想符合
        String inSessionOtpCode= (String) this.httpServletRequest.getSession().getAttribute(telphone);
        if(!com.alibaba.druid.util.StringUtils.equals(otpCode,inSessionOtpCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合");
        }

        //用户注册方法
        UserVO userVO=userUtil.registerUser(username,realname,telphone,password,clazz,sno,dormitory,gender);
        userService.register(userVO);

        return CommonReturnType.create(null);
    }


    /*
    * 获取验证码
    * 电话号码 telphone
    * */
    @RequestMapping(value = "/getotp",method ={RequestMethod.POST})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone") String telphone){
        //按照一定的规则生成OTP验证码
        int randomInt= userUtil.getotp();
        String otpCode=String.valueOf(randomInt);

        //将OPT验证码同对应用户的手机号关联,使用httpsession的方式绑定手机号和otpcode
        httpServletRequest.getSession().setAttribute(telphone,otpCode);

        //OTP验证码通过短信通道发给用户
        System.out.println("telphone="+telphone+"&otpCode="+otpCode);
        return CommonReturnType.create(null);
    }

    /*
    * 查询用户发布的商品
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/GetUserGoods")
    public String GetUserGoods(Model model) throws BusinessException {

        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

       /* PageHelper.startPage(start/length+1,length);*/
        List<GoodsDO> goodsDOList=userService.getallgoods(userVO.getId());

       /* PageInfo<GoodsDO> pageInfo=new PageInfo<GoodsDO>(goodsDOList);

        model.addAttribute("pageInfo1",pageInfo);*/

        model.addAttribute("list",goodsDOList);
        return CommonReturnType.success("ht/mygoods");
    }
    /*
    * 查询用户订单
    * */
    @RequiresRoles("user")
    @RequestMapping(value = "/GetUserOrder")
    public String GetUserOrder(Model model) throws BusinessException {
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        List<OrderListVO>orderListVOs=userService.getallorder(userVO.getId());

        model.addAttribute("userorderlist",orderListVOs);

        return CommonReturnType.success("ht/myorder");
    }
    /*
    * 获取所有用户
    * */
    @RequiresRoles("admin")
    @RequestMapping(value = "/GetAllUser")
    public String GetAllUser(Model model){
       /* UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");*/

        List<UserVO>userVOList=userService.Getalluser();

        model.addAttribute("userlist",userVOList);

        return  CommonReturnType.success("ht/userlist");
    }

    /*
    * 退出登录
    * */
    @RequestMapping("logout")
    public  String logout(){
        httpServletRequest.getSession().removeAttribute("user");
        return CommonReturnType.success("logout");
    }
    /*
    * 查询用户信息
    * */
    @RequestMapping("selectuser")
    public String selectuser(Model model) throws BusinessException {
        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        UserDO userDO=userService.select(userVO.getId());

        model.addAttribute("user",userDO);
        return CommonReturnType.success("ht/pin");
    }

    /*
    * 修改用户信息
    * */
    @RequiresRoles("user")
    @RequestMapping("changeuser")
    @ResponseBody
    public CommonReturnType changeuser(@RequestParam(name ="username")String username,
                                       @RequestParam(name ="telphone") String telphone,
                                       @RequestParam(name ="clazz")String clazz,
                                       @RequestParam(name ="sno")Integer sno,
                                       @RequestParam(name ="dormitory")String dormitory) throws BusinessException {

        UserVO userVO= (UserVO) httpServletRequest.getSession().getAttribute("user");

        UserDO userDO=new UserDO();
        userDO.setId(userVO.getId());
        userDO.setUsername(username);
        userDO.setClazz(clazz);
        userDO.setTelphone(telphone);
        userDO.setSno(sno);
        userDO.setDormitory(dormitory);

        userService.changeuser(userDO);

        return CommonReturnType.create(null);
    }

    @RequestMapping("deluser")
    @ResponseBody
    @RequiresRoles("root")
    public CommonReturnType deluser(@RequestParam("id") Integer id) throws BusinessException {

        userService.deluser(id);
        return CommonReturnType.create(null);
    }
}
