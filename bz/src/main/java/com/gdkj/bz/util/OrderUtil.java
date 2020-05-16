package com.gdkj.bz.util;

import com.gdkj.bz.controller.VO.OrderListVO;
import com.gdkj.bz.entity.GoodsDO;
import com.gdkj.bz.entity.OrderDO;
import org.springframework.stereotype.Component;

/**
 * Created by DELL on 2019/12/6.
 */
@Component
public class OrderUtil {
    public OrderListVO convertFromentity2(GoodsDO goodsDO, OrderDO orderDO){
        OrderListVO orderListVO=new OrderListVO();
        orderListVO.setId(orderDO.getId());
        orderListVO.setAmount(orderDO.getAmount());
        orderListVO.setPayment(orderDO.getPayment());
        orderListVO.setCreatetime(orderDO.getCreatetime());
        orderListVO.setSubtotal(orderDO.getSubtotal());
        orderListVO.setGoodsname(goodsDO.getGoodsname());
        orderListVO.setPrice(goodsDO.getPrice());
        return orderListVO;
    }
}
