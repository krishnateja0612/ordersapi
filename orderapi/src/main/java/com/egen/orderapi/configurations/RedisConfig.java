package com.egen.orderapi.configurations;
//import java.time.Duration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

//docker run -d -p 6379:6379 redis
@Configuration
@EnableCaching
//@PropertySource("classpath:application.properties")
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
//   @Autowired
//   private Environment env;
//
//   @Bean
//   public LettuceConnectionFactory redisConnectionFactory() {
//	RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
//	redisConf.setHostName(env.getProperty("spring.redis.host"));
//	redisConf.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
//	redisConf.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
//        return new LettuceConnectionFactory(redisConf);
//   }
//   @Bean
//   public RedisCacheConfiguration cacheConfiguration() {
//	RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
//	  .entryTtl(Duration.ofSeconds(600))
//	  .disableCachingNullValues();
//	return cacheConfig;
//   }
//   @Bean
//   public RedisCacheManager cacheManager() {
//	RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory())
//	  .cacheDefaults(cacheConfiguration())
//	  .transactionAware()
//	  .build();
//	return rcm;
//   }
//}
