package zwzs2016.com.github.AppBeans;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getRedis() {
        redisTemplate.opsForValue().set("java","java is one");
        String str = (String) redisTemplate.opsForValue().get("java");
        System.out.println(str);
    }
}

