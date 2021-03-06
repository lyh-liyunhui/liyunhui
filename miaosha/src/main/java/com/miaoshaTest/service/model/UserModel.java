package com.miaoshaTest.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by DELL on 2019/9/10.
 */
public class UserModel {
    private  Integer id;

    @NotBlank(message = "用户名不能为空")
    private  String name;
    @NotNull(message = "性别不能不填写")
    private  Byte gender;

    @NotNull(message = "年龄不能不填写")
    @Min(value = 0,message ="年龄必须大于0")
    @Max(value = 150,message = "年龄必须小于150岁")
    private  Integer age;
    @NotBlank(message = "手机号不能为空")
    private  String telphone;
    private  String registerMode;
    private  String thirdPartyId;
    @NotBlank(message = "密码不能为空")
    private String encrptPassword;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

    public String getRegisterMode() {
        return registerMode;
    }

    public void setRegisterMode(String registerMode) {
        this.registerMode = registerMode;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }


}
