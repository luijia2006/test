package com.we.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Event工厂
 */
public class LongEventFactory implements EventFactory<LongEvent>
{
    public LongEvent newInstance()
    {
        return new LongEvent();
    }
}