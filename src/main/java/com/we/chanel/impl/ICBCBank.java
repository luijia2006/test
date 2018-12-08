package com.we.chanel.impl;

import com.we.annotation.Pay;
import com.we.chanel.Strategy;

@Pay(channelId = 1)
public class ICBCBank implements Strategy {
    @Override
    public String calPay(int channelId, int goodsId) {
        System.out.println("channelId = [" + channelId + "], goodsId = [" + goodsId + "]");
        return "channelId = [" + channelId + "], goodsId = [" + goodsId + "]";
    }
}
