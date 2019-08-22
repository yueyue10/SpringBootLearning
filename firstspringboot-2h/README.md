# 搭建第一个sping boot 程序

参考：
https://blog.csdn.net/forezp/article/details/61472783

本文出自方志朋的博客

## 学习记录：

### 个人发现知识

利用idea测试接口
Tools -> Http Client -> Test RESTFul Web Server
![rest1.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/firstspringboot-2h/rest/rest1.png)
![rest2.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/firstspringboot-2h/rest/rest2.png)

基础介绍：
---
```
server:
  port: 8080
  context-path: /springboot
girl:
  name: B
  age: 18
  content: content:${name},age:${age}
spring:
  profiles:
    active: prod
```
* application.yml文件解析
    * 程序的端口为8080,context-path为 /springboot：
    * application里面定义的属性可以使用${xxx}获取到
    * 在代码中可以通过@Value("${name}")获取到
    * profiles:active:制定不同环境的配置
```
@RestController     //等同于同时加上了@Controller和@ResponseBody
public class HelloController {
   
    //访问/hello或者/hi任何一个地址，都会返回一样的结果
    @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET)
    public String say(){
        return "hi you!!!";
    }
}
```
* Controller类解析
    * @RestController     //等同于同时加上了@Controller和@ResponseBody
    * @RequestMapping(value = {"/hello","/hi"},method = RequestMethod.GET) //访问/hello或者/hi任何一个地址，都会返回一样的结果
    * 运行 Application的main(),程序会启动。打开浏览器输入网址：localhost:8080/springboot/hi
```
@ConfigurationProperties(prefix="girl")
@Component
public class GirlProperties {

    private String name;
    private int age;
    ...
}
```
* GirlProperties类解析
    * 通过ConfigurationProperties注解，将属性注入到bean中
    * 通过Component注解将bean注解到spring容器中：

jpa方式操作数据库
---
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
* 导入jar ，在pom.xml中添加依赖:
```
spring:
  profiles:
    active: prod
    
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/dbgirl?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8
    username: root
    password: 123

  jpa:
    hibernate:
      ddl-auto: create
    show-sql: true
```
* 在application.yml中添加数据库配置：
    * 其中ddl_auto: create 代表在数据库创建表，update 代表更新，
    * 首次启动需要create ,如果你想通过hibernate 注解的方式创建数据库的表的话，之后需要改为 update.
```
@Entity
public class Girl {

    @Id
    @GeneratedValue
    private Integer id;
    private String cupSize;
    private Integer age;

    public Girl() {
    }
    ...
```
* 创建一个实体girl，这是基于hibernate的:
```
//其中第二个参数为Id的类型
public interface GirlRep extends JpaRepository<Girl,Integer>{
   }
```
* 创建Dao接口, springboot 将接口类会自动注解到spring容器中，不需要我吗做任何配置，只需要继承JpaRepository 即可：
```
@RestController
public class GirlController {

    @Autowired
    private GirlRep girlRep;

    /**
     * 查询所有女生列表
     * @return
     */
    @RequestMapping(value = "/girls",method = RequestMethod.GET)
    public List<Girl> getGirlList(){
        return girlRep.findAll();
    }

    /**
     * 添加一个女生
     * @param cupSize
     * @param age
     * @return
     */
    @RequestMapping(value = "/girls",method = RequestMethod.POST)
    public Girl addGirl(@RequestParam("cupSize") String cupSize,
                        @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRep.save(girl);
    }
   } 
```
* 创建一个GirlController，写一个获取所有girl的api和添加girl的api ，自己跑一下就可以了:
 

