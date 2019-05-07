package com.we.designpattern.factory;

import com.we.designpattern.factory.channel.Strategy;

public class Content {
    public String calPay(int channelId,int goodsId){
        Strategy strategy = StrategyFactory.getInstance().create(channelId);
        return strategy.calPay(channelId,goodsId);
    }
}
