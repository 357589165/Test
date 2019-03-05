package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author 王超 by 2019-03-05
 */
@Configuration
public class RedisConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        // 创建RedisTemplate
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接
        template.setConnectionFactory(factory);
        // 设置默认序列化器
        template.setDefaultSerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public DefaultRedisScript<Boolean> defaultRedisScript(){
        // 实例化redis 脚本
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        // 创建脚本文件
        ScriptSource scriptSource = new ResourceScriptSource(new ClassPathResource("redis.lua"));
        // 设置redis 脚本文件
        redisScript.setScriptSource(scriptSource);
        // 设置脚本返回值类型
        redisScript.setResultType(Boolean.class);
        return redisScript;
    }

}
