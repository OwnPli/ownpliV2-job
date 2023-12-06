package db.project.ownpliv2.config.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class TrackRedisConfig extends RedisConfig {

    @Bean
    @Primary
    public RedisConnectionFactory defaultRedisConnectionFactory() {
        return redisConnectionFactory(1);
    }

    @Bean
    @Qualifier("albumRedisTemplate")
    public RedisTemplate<String, Object> trackRedisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }

    @Bean(name = "albumStringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate() {
        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(defaultRedisConnectionFactory());
        return stringRedisTemplate;
    }
}