package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class RedisService {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @SneakyThrows
    public void setValue(String key, Object value) {
        String hashValue = objectMapper.writeValueAsString(value);
        try {
            redisTemplate.opsForValue().set(key, hashValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setExpire(String key, int timeOut) {
        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    public <T> T getValue(String key, Class<T> classValue) {
        T target = null;
        try {
            Object temp = redisTemplate.opsForValue().get(key);
            if (temp != null) {
                target = objectMapper.readValue(temp.toString(), classValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
