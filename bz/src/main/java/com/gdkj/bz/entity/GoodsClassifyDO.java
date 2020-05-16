package com.gdkj.bz.entity;


/*
* 商品分类表
* */
public class GoodsClassifyDO {
    private Integer id;

    private String classifyname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassifyname() {
        return classifyname;
    }

    public void setClassifyname(String classifyname) {
        this.classifyname = classifyname == null ? null : classifyname.trim();
    }
}