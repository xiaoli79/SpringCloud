package com.xiaoli.user;


import common.xiaoli.common.utils.Redis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RedisClientTest {



    @Autowired
    private Redis redis;

    @Test
    public void test() {
        redis.set("aa","cc");
    }
}
