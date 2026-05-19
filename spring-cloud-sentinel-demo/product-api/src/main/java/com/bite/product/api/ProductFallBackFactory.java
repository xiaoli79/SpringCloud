package com.bite.product.api;

import com.bite.product.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;



@Slf4j
public class ProductFallBackFactory implements FallbackFactory<ProductApi> {
    @Override
    public ProductApi create(Throwable cause) {
        return new ProductApi() {
            @Override
            public ProductInfo getProductInfo(Integer productId) {

                log.error("获取商品信息失败");
                return new ProductInfo();
            }

            @Override
            public String p1(Integer id) {

                log.error("p1请求失败");
                return "p1请求失败";
            }

            @Override
            public String p2(Integer id, String name) {
                log.error("p2请求失败");
                return "p2请求失败";
            }

            @Override
            public String p3(ProductInfo productInfo) {
                log.error("p3请求失败");
                return "p3请求失败";
            }

            @Override
            public String p4(ProductInfo productInfo) {
                log.error("p4请求失败");
                return "p4请求失败";
            }
        };
    }
}
