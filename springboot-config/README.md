# Spring Boot配置文件详解

参考：
https://blog.csdn.net/forezp/article/details/70437576

本文出自方志朋的博客

### 知识点记录：

```
my:
 name: forezp
 age: 12
 number:  ${random.int}
 uuid : ${random.uuid}
 max: ${random.int(10)}
 value: ${random.value}
 greeting: hi,i'm  ${my.name}
```
* **application.yml文件解析**
    * 创建javabean-my，包含多个配置属性
    * ${random} ，它可以用来生成各种不同类型的随机值。
    * 将属性值赋于给ConfigBean。
        * @ConfigurationProperties，并加上它的prrfix
        * @Component可加可不加
        * 另外spring-boot-configuration-processor依赖可加可不加
        ```
        @ConfigurationProperties(prefix = "my")
        @Component
        public class ConfigBean {
        
            private String name;
            ....
        ```
    * 使用ConfigBean。
        * 需要在应用类或者application类，加EnableConfigurationProperties注解。
        ```
        @RestController
        @EnableConfigurationProperties({ConfigBean.class})
        public class LucyController {
            @Autowired
            ConfigBean configBean;
            ....
        ```

---
```
com.forezp.name=forezp
com.forezp.age=12
```
- **自定义配置文件,如test.properties:**
    - 将这个配置文件信息赋予给User 
        - 加上@Configuration
        - 加上@PropertySource(value = “classpath:test.properties”)
        - 加上@ConfigurationProperties(prefix = “com.forezp”)
        ```
        @Configuration
        @PropertySource(value = "classpath:test.properties")
        @ConfigurationProperties(prefix = "com.forezp")
        public class User {
            private String name;
            private int age;
            ....
        ```
    - 使用User。
        - 加上@EnableConfigurationProperties({ConfigBean.class,User.class})
        ```
        @RestController
        @EnableConfigurationProperties({User.class})
        public class LucyController {
            @Autowired
            ConfigBean configBean;
            ....
        ```
- **多个环境配置文件**
    - 配置不同的环境。
        - 格式为application-{profile}.properties，其中{profile}对应你的环境标识
            ```
            application-test.properties：测试环境
            application-dev.properties：开发环境
            application-prod.properties：生产环境
            ```
    - 在application.yml中使用配置
        ```
        spring:
          profiles:
            active: dev
        ```