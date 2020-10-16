# 概述

​	在java 生态系统中，除了很适合企业开发的项目以外，开发往往会趋于复杂化，spring boot是一个可以减少java 开发成本的框架，在这个框架中有很多其他方面的依赖可以进行管理和java化，比如：

​	依赖配置优化，我们可以使用工具帮助管理这些依赖关系，比如简化这种配置来达到简单开发的效果。

...

 项目将建立在spring boot的基础之上，完成java web方向和面向对象编程的开发进行优化。

## 启动项目

- ### 运行环境

  **java jdk 1.8**

spring boot 启动项目 进入Jvite config 配置界面 ["localhost:8090/Jvite"]()

- 配置json-view

  [localhost:8090/Jvite]()，进入后即可安装Json-view

  

- 文件的配置

  ProjectConfiguration.class 创建一个可以配置文件的class,通过注解的形式:

  *设置Config配置文件的路径:*

  **@JviteConfig**

  ​	定义配置文件的路径，参数文件的路径，例如:

  ```
  @JviteConfig("src/main/resources/static/json/")
  ```

  ***通过json文件配置:***

  **@JviteAfterType**

  ​	定义配置文件的根节点，参数为节点名字 例如:
  
  ```
  @JviteAfterType("spring")
private List<String> spring;
  ```

  **@JviteJsonFileName**

  ​	定义json配置文件的文件名，参数为文件名 例如:
  
  ```
  @JviteJsonFileName("server")
  private String servernode;
  ```
  
  ***通过yml文件配置:***
  
  **@JviteYmlFileName**
  
  定义yml配置文件的文件名,参数为文件名 例如:
  
  ```
  @JviteYmlFileName("server")
  private String server;
  ```
  
  ***通过properties文件配置:***
  
  **@JvitePropertiesFileName**
  
  定义properties配置文件的文件名,参数为文件名 例如:
  
  ```
  @JvitePropertiesFileName("spring")
  private String spring;
  ```

- 配置文件的生成

  使用BuildConfiguration,实例化BuildConfiguration，参数为ProjectConfiguration对象 例如:

  ```
  ProjectConfiguration pro=new ProjectConfiguration();
  BuildConfiguration.filename="src/main/resources/static/yml/application.yml";//设置文件输出文件名
  BuildConfiguration buildConfiguration = new BuildConfiguration(pro);
  ```

- 自动配置

  将BuildConfiguration.class和ProjectConfiguration.class 注册到bean容器中 例如：

  ```
  @Bean
  public ProjectConfiguration projectConfiguration(){
      return new ProjectConfiguration();
  }
  @Bean
  public BuildConfiguration buildConfiguration(ProjectConfiguration pro){
      BuildConfiguration.filename="src/main/resources/application.yml";//输入路径,默认值
      return new BuildConfiguration(pro);
  }
  ```

  注: *使用自动配置时，需要重新编译，Build Project 重置配置文件。*

  

- ### 属性设置

-    快速配置

  可以在pom.xml中引入依赖,自动化使用set,get的配置:

  ```
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <scope>provided</scope>
  </dependency>
  ```

  将其注解在ProjectConfiguration.class上，例如：

  ```
  @Setter
  @Getter
  public class ProjectConfiguration {
  	//code...
  }
  ```

  在每一个List 初始节点中定义set方法，例如：

  ```
  public void setServer(){
      this.server=new ArrayList<>();
      this.server.add(this.servernode);
  }
  ```

