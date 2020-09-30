# 概述

​	在java 生态系统中，除了很适合企业开发的项目以外，个人的开发往往显得很复杂，spring boot虽然是一套可以减少java 开发成本的框架，但是在这个框架中有很多其他方面的依赖，比如：

​	maven依赖配置很繁琐，我们可以使用一种新的概念，比如简化这种配置来达到简单开发的效果，随着java和其他技术的发展，java技术不得变得简单而不失去性能才能适应适应企业和个人的开发工作。

...

 项目将建立在spring boot的基础之上，完成java web方向和面向对象编程的繁琐进行优化。

## 启动项目

- ### 运行环境

  **java jdk 1.8**

spring boot 启动项目 进入Jvite config 配置界面 ["localhost:8090/Jvite"]()

- 配置json-view

  [localhost:8090/Jvite]()，进入后即可安装Json-view

- 文件的配置

  ProjectConfiguration.class 创建一个可以配置文件的class,通过注解的形式:

  

  **@JviteConfig**

  ​	定义配置文件的路径，参数文件的路径，例如:

  ```
  @JviteConfig("src/main/resources/static/json/")
  ```

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