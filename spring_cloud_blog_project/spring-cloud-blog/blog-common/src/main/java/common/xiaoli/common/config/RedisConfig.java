package common.xiaoli.common.config;


import common.xiaoli.common.utils.Redis;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

    @Bean
//  只要有了这个配置，这个Redis才会被配置~~
    @ConditionalOnProperty(prefix = "spring.data.redis",name="host")
    public Redis redis(StringRedisTemplate stringRedisTemplate) {
        return new Redis(stringRedisTemplate);
    }
}
