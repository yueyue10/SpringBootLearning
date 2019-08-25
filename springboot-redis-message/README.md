# 第九篇： springboot整合Redis 
## 十四篇：在springboot中用redis实现消息队列

参考：https://blog.csdn.net/forezp/article/details/70662983

参考：https://www.jianshu.com/p/93727fa9bf23

参考：https://blog.csdn.net/forezp/article/details/71023652

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter-data-redis
* application.properties文件配置
    * 配置redis相关信息
    * 如果你的redis有密码，配置下即可。
    
> 经过上述两步的操作，你可以访问redis数据了。

* 具体编码 > 测试Redis数据存储
    * 编写dao层代码
        * 通过StringRedisTemplate来访问redis.
    * 编写单元测试类进行测试
        * 测试类添加@RunWith(SpringRunner.class)注解
        * 测试类使用@SpringBootTest注解
        * 测试方法上加@Test注解
        * 引入RedisDao进行测试。

* 具体代码 > 测试Redis消息队列
    * 创建一个消息接收者Receiver，需要注入到springboot中。
    * 在Application类中注入Redis相关类
        * 注入消息接收者Receiver
        * 注入消息监听容器
        * 注入StringRedisTemplate
    * 在Application的main方法里面编写测试消息代码
    
> 在spring data redis中，利用redis发送一条消息和接受一条消息，需要三样东西：
>* 一个连接工厂
>* 一个消息监听容器
>* Redis template