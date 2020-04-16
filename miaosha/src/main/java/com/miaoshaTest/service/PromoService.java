package com.miaoshaTest.service;

import com.miaoshaTest.service.model.PromoModel;

/**
 * Created by DELL on 2019/9/21.
 */
public interface PromoService {
    PromoModel getPromoByItemId(Integer itemId);
}
