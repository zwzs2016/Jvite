package zwzs2016.com.github.AppConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("zwzs2016.com.github.AppConfig")
public class WebbeanConfig {
    @Bean
    public ProjectConfiguration projectConfiguration(){
        return new ProjectConfiguration();
    }
    @Bean
    public ProjectConfiguration_yml projectConfiguration_yml(){
        return new ProjectConfiguration_yml();
    }
    @Bean
    public ProjectConfiguration_properties projectConfiguration_properties(){
        return new ProjectConfiguration_properties();
    }
    @Bean
    public BuildConfiguration buildConfiguration(ProjectConfiguration_yml pro){
        BuildConfiguration.filename="src/main/resources/application.yml";
        return new BuildConfiguration(pro);
    }
}
