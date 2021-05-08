### 功能
* 创建独立的Spring applications
* 能够使用内嵌的Tomcat, Jetty or Undertow，不需要部署war
* 提供starter pom来简化maven配置
* 自动配置Spring
* 提供一些生产环境的特性，比如metrics, health checks and externalized configuration
* 绝对没有代码生成和XML配置要求

### 核心注解类说明
```@RestController```
就是@Controller+@ResponseBody组合，支持Restful访问方式，返回结果都是json字符串

```@SpringBootApplication```
就是@SpringBootConfiguration+@EnableAutoConfiguration+
@ComponentScan等组合在一下，非常简单，使用也方便

```@SpringBootTest```
Spring Boot版本1.4才出现的，具有Spring Boot支持的引导程序（例如，加载应用程序、属性，为我们提供Spring Boot的所有精华部分）
关键是自动导入测试需要的类。。。