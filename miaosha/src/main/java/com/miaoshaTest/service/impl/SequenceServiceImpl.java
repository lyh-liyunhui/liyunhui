package com.miaoshaTest.service.impl;

import com.miaoshaTest.dao.SequenceDOMapper;
import com.miaoshaTest.dataobject.SequenceDO;
import com.miaoshaTest.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by DELL on 2019/9/21.
 */
@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    SequenceDOMapper sequenceDOMapper;

    //订单号生成
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNO() {
        //1.订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);

        //2.中间六位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        //凑足六位
        String sequenceStr = String.valueOf(sequence);
        for(int i = 0; i < 6 - sequenceStr.length(); i++) {
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);

        //3.最后两位分库分表位
        stringBuilder.append("00");
        return stringBuilder.toString();
    }
    }
