package zwzs2016.com.github.AppConfig;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import zwzs2016.com.github.AppAnnotations.JviteConfig;
import zwzs2016.com.github.AppAnnotations.JviteYmlFileName;

@JviteConfig("src/main/resources/static/yml/")
@Getter
@Setter
public class ProjectConfiguration_yml {
    @JviteYmlFileName("server")
    private String server;
    @JviteYmlFileName("spring")
    private String spring;
    @JviteYmlFileName("redis")
    private  String redis;
    @JviteYmlFileName("logging")
    private  String logging;

    @Test
    public void test(){
        ProjectConfiguration_yml pro=new ProjectConfiguration_yml();
        BuildConfiguration buildConfiguration=new BuildConfiguration(pro);
    }
}
