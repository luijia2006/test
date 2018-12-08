package com.we.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 5.消费者
 */
public class LongEventHandler implements EventHandler<LongEvent>
{
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch)
    {
        System.out.println("Event: " + event.getValue());
    }
}