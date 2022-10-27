package vn.unicloud.umeepay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Log4j2
@RequiredArgsConstructor
public class RedisService {

    private final ObjectMapper objectMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, Object value) {
        try {
            String hashValue = objectMapper.writeValueAsString(value);
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
            log.error("Get value error: {}", e.getMessage());
        }
        return target;
    }

    public boolean exist(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
