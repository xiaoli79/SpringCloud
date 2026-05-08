package org.example.productservice.service;


import org.example.model.ProductInfo;
import org.example.productservice.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    @Autowired
    private ProductMapper productMapper;

     public ProductInfo selectProductInfoByProductId(Integer productId){
        return productMapper.selectProductInfoById(productId);
    }
}
