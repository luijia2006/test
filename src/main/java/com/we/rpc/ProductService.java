package com.we.rpc;

public interface ProductService {

    /**
     * 根据商品ID查询商品信息
     *
     * @param id：商品id
     * @return
     */
    Product selectProductById(long id);
}