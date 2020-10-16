package zwzs2016.com.github.AppConfig;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import zwzs2016.com.github.AppAnnotations.JviteConfig;
import zwzs2016.com.github.AppAnnotations.JvitePropertiesFileName;

@JviteConfig("src/main/resources/static/properties/")
@Setter
@Getter
public class ProjectConfiguration_properties {
    @JvitePropertiesFileName("spring")
    private String spring;
    @JvitePropertiesFileName("redis")
    private String redis;
    @JvitePropertiesFileName("server")
    private String server;
    @JvitePropertiesFileName("logging")
    private String logging;

    @Test
    public void test(){
        ProjectConfiguration_properties pro=new ProjectConfiguration_properties();
        BuildConfiguration buildConfiguration=new BuildConfiguration(pro);
    }
}
