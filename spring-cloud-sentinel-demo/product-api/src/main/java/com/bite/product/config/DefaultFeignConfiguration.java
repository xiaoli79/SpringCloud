package com.bite.product.config;

import com.bite.product.api.ProductFallBackFactory;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {

    @Bean
    public ProductFallBackFactory productFallBackFactory(){
        return new ProductFallBackFactory();
    }
}
