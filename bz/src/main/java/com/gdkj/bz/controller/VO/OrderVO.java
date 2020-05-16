package com.gdkj.bz.controller.VO;

import java.math.BigDecimal;

/**
 * Created by DELL on 2019/11/18.
 */
public class OrderVO {

    private Integer goodsId;

    private Integer amount;

    private BigDecimal subtotal;

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}




