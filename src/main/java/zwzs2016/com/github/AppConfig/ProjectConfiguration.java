package zwzs2016.com.github.AppConfig;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import zwzs2016.com.github.AppAnnotations.JviteAfterType;
import zwzs2016.com.github.AppAnnotations.JviteConfig;
import zwzs2016.com.github.AppAnnotations.JviteJsonFileName;

import java.util.ArrayList;
import java.util.List;
@JviteConfig("src/main/resources/static/json/")
@Setter
@Getter
public class ProjectConfiguration {
    @JviteJsonFileName("server")
    private String servernode;

    @JviteJsonFileName("datasource")
    private String datasource;

    @JviteJsonFileName("resources")
    private String resources;

    @JviteJsonFileName("thymeleaf")
    private String thymeleaf;

    @JviteJsonFileName("redis")
    private String redisnode;

    @JviteAfterType("spring")
    private List<String> spring;

    @JviteAfterType("redis")
    private List<String> redis;

    @JviteAfterType("server")
    private List<String> server;

    public void setSpring(){
        this.spring=new ArrayList<>();
        this.spring.add(this.datasource);
        this.spring.add(this.resources);
        this.spring.add(this.thymeleaf);
    }

    public void setRedis(){
        this.redis=new ArrayList<>();
        this.redis.add(this.redisnode);
    }

    public void setServer(){
        this.server=new ArrayList<>();
        this.server.add(this.servernode);
    }

    @Test
    public void test(){
        ProjectConfiguration pro=new ProjectConfiguration();
//        BuildConfiguration.filename="src/main/resources/static/yml/application.yml";
        BuildConfiguration buildConfiguration = new BuildConfiguration(pro);
    }
}
