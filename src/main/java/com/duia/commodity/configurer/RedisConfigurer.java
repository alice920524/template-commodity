package com.duia.commodity.configurer;

import com.duia.commodity.configurer.redis.CommonRedis;
import com.duia.commodity.configurer.redis.RedisConfigBean;
import com.duia.commodity.configurer.redis.SessionRedis;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by admin on 2017/3/11.
 */
@Configuration
@EnableCaching
public class RedisConfigurer extends CachingConfigurerSupport {

    @Autowired
    private SessionRedis sessionRedis;
    @Autowired
    private CommonRedis commonRedis;

    @Bean
    public CacheManager cacheManager(
            @SuppressWarnings("rawtypes") RedisTemplate<?, ?> redisTemplate) {
        // 创建缓存管理器
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        // 设置缓存过期时间（秒）
        cacheManager.setDefaultExpiration(3600 * 24);
        return cacheManager;
    }

    @Bean
    @Primary
    public RedisTemplate redisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate(connectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 只用来获取 微信access_token, 其它使用另行配置
     *
     * @return
     */
    @Bean(name = "binaryRedisTemplate")
    public RedisTemplate sessionRedisTemplate() {
        RedisTemplate template = new RedisTemplate();
        template.setKeySerializer(new StringRedisSerializer());
        template.setConnectionFactory(sessionJedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }

    private RedisConnectionFactory connectionFactory() {
        RedisConnectionFactory factory = initFactory(commonRedis);
        return factory;
    }

    private RedisConnectionFactory sessionJedisConnectionFactory() {
        RedisConnectionFactory factory = initFactory(sessionRedis);
        return factory;
    }

    /**
     * init
     *
     * @param redis
     * @return
     */
    private JedisConnectionFactory initFactory(RedisConfigBean redis) {
        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(redis.getHost());
        jedis.setPort(redis.getPort());
        if (!StringUtils.isEmpty(redis.getPassword())) {
            jedis.setPassword(redis.getPassword());
        }
        if (redis.getDatabase() != 0) {
            jedis.setDatabase(redis.getDatabase());
        }
        jedis.setTimeout(redis.getTimeout());
        jedis.setPoolConfig(poolCofig(redis.getMaxIdle(),
                redis.getMaxTotal(), redis.getMaxWaitMillis(), redis.isTestOnBorrow(), redis.isTestOnReturn()));
        jedis.afterPropertiesSet(); // 初始化连接pool
        return jedis;
    }

    private JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis,
                                      boolean testOnBorrow, boolean testOnReturn) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        poolCofig.setTestOnReturn(testOnReturn);
        return poolCofig;
    }


}
