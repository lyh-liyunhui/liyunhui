package com.gdkj.bz.controller.VO;

import com.gdkj.bz.entity.PermissionDO;
import com.gdkj.bz.entity.RoleDO;

import java.util.Date;
import java.util.List;

/**
 * Created by DELL on 2019/11/12.
 */
public class UserVO {
    private Integer id;

    private String username;

    private String realname;

    private String telphone;

    private String password;

    private String clazz;

    private Integer sno;

    private String dormitory;

    private Byte gender;

    private Date createtime;

    private Date modifytime;

    private String avatar;

    public List<PermissionDO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDO> permissions) {
        this.permissions = permissions;
    }

    public List<RoleDO> getRole() {
        return role;
    }

    public void setRole(List<RoleDO> role) {
        this.role = role;
    }

    private List<RoleDO> role;

    private List<PermissionDO> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
