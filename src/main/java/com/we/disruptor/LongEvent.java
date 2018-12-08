package com.we.disruptor;

import lombok.Data;

/**
 * 2.数据对象：
 */
@Data
public class LongEvent
{
    private long value;

    public void set(long value)
    {
        this.value = value;
    }

}