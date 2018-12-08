package com.we.factory;

import com.we.chanel.Strategy;

public class Content {
    public String calPay(int channelId,int goodsId){
        Strategy strategy = StrategyFactory.getInstance().create(channelId);
        return strategy.calPay(channelId,goodsId);
    }
}
