package com.smart.cache.config;

import com.smart.cache.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

//@CacheConfig(cacheNames = "users",keyGenerator="MyKeyGenerator ") //缺少源代码 没有这个自定义 P504, P499
public class UserService {
        @Cacheable
        public User findA(User user) {
            return null;
        }

        @Cacheable
        public User findB(User user) {
            return null;
        }
}
