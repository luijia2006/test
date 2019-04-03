package com.we.rpc;

public class ProductServiceImpl implements ProductService {
    public Product selectProductById(long id) {
        Product product = new Product();
        product.setId(id);
        product.setName("CAMEL运动鞋");
        product.setPrice(299.0);
        return product;
    }
}