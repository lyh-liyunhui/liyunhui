package com.miaoshaTest.service.model;

import java.math.BigDecimal;

/**
 * Created by DELL on 2019/9/21.
 */
//用户下单的交易模型
public class OrderModel {
    private String id;

    //购买用户id
    private  Integer userId;


    //购买的商品id
    private Integer itemId;

    //购买商品的单价
    private BigDecimal itemPrice;

    //购买数量
    private Integer amount;
    //购买金额
    private BigDecimal orderprice;
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(BigDecimal orderprice) {
        this.orderprice = orderprice;
    }
}
