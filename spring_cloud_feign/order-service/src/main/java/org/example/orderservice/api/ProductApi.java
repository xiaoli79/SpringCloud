package org.example.orderservice.api;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value="product-service",path="product")
public interface ProductApi extends org.example.api.ProductApi {

}
