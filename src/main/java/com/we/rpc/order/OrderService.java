package com.we.rpc.order;

public class OrderService {
    public Order selectOrderById(long id){
        Order order = new Order();
        order.setId(id);
        order.setUserId(1l);
        order.setProductId(1L);
        return order;
    }
}