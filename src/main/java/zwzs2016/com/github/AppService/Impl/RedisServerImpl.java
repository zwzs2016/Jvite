package zwzs2016.com.github.AppService.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import zwzs2016.com.github.AppService.RedisService;

@Service
public class RedisServerImpl implements RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String redisconntest() {
        redisTemplate.opsForValue().set("java","java is one");
        String str = (String) redisTemplate.opsForValue().get("java");
        String str1=(String) redisTemplate.opsForValue().get("name");
        System.out.println(str);
        return str;
    }
}
