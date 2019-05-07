package com.we.designpattern.factory.channel.impl;

import com.we.designpattern.factory.annotation.Pay;
import com.we.designpattern.factory.channel.Strategy;

@Pay(channelId = 1)
public class ICBCBank implements Strategy {
    @Override
    public String calPay(int channelId, int goodsId) {
        System.out.println("channelId = [" + channelId + "], goodsId = [" + goodsId + "]");
        return "channelId = [" + channelId + "], goodsId = [" + goodsId + "]";
    }
}
