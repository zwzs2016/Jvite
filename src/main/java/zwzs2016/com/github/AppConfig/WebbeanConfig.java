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
    public BuildConfiguration buildConfiguration(ProjectConfiguration pro){
        BuildConfiguration.filename="src/main/resources/application.yml";
        return new BuildConfiguration(pro);
    }
}
