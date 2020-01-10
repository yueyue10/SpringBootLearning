# Spring Boot配置文件详解

参考：
https://blog.csdn.net/forezp/article/details/70437576

本文出自方志朋的博客

### 项目介绍：

一、application.yml文件内容介绍：
---

#### 1.配置一个全局对象的
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
* 创建了对象javabean-my，包含多个配置属性
* ${random} ，它可以用来生成各种不同类型的随机值。

#### 2.配置多环境
```
spring:
  profiles:
    active: dev
```
- 配置不同的环境。
    - 格式为application-{profile}.properties，其中{profile}对应你的环境标识
    ```
    application-test.properties：测试环境
    application-dev.properties：开发环境
    application-prod.properties：生产环境
    ``` 

二、自定义配置文件test.properties
---

```
com.forezp.name=forezp
com.forezp.age=12
```
配置两个属性值

三、全局对象my的使用
---

#### 1.在代码中创建ConfigBean类与之关联
```
@ConfigurationProperties(prefix = "my")
@Component
public class ConfigBean {

    private String name;
    ....
```

* 添加注解@ConfigurationProperties，并加上它的prrfix
* 注解@Component可加可不加
* 另外pom中spring-boot-configuration-processor依赖可加可不加

#### 2.在代码中使用全局对象
```
@RestController
@EnableConfigurationProperties({ConfigBean.class})
public class LucyController {
    @Autowired
    ConfigBean configBean;
    ....
```
* 使用ConfigBean。
    * 需要在应用类或者application类，加EnableConfigurationProperties注解。
    * 使用@Autowired注解将属性值赋于给configBean。

---

四、自定义配置文件test.properties的使用
---

#### 1.创建User类与之关联
```
@Configuration
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "com.forezp")
public class User {
    private String name;
    private int age;
    ....
```
- 将这个配置文件信息赋予给User 
    - 加上@Configuration
    - 加上@PropertySource(value = “classpath:test.properties”)
    - 加上@ConfigurationProperties(prefix = “com.forezp”)

#### 2.使用配置文件信息
```
@RestController
@EnableConfigurationProperties({User.class})
public class LucyController {
    @Autowired
    ConfigBean configBean;
    ....
```
- 使用User。
    - 加上@EnableConfigurationProperties({ConfigBean.class,User.class})
        
五、启动服务，测试各接口
---

| 接口名称               |          接口地址           |
| --------              |          :----:             |
| 获取全局属性值接口      |          http://localhost:8082/miya             |
| 获取全局对象接口	    |          http://localhost:8082/lucy             |
| 获取全局配置信息接口	|          http://localhost:8082/user             |
        