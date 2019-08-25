# 第九篇： springboot整合Redis

参考：https://blog.csdn.net/forezp/article/details/70662983

参考：https://www.jianshu.com/p/93727fa9bf23

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter-data-redis
* application.properties文件配置
    * 配置redis相关信息
    * 如果你的redis有密码，配置下即可。
    
> 经过上述两步的操作，你可以访问redis数据了。

* 具体编码
    * 编写dao层代码
        * 通过StringRedisTemplate来访问redis.
    * 编写单元测试类进行测试
        * 测试类添加@RunWith(SpringRunner.class)注解
        * 测试类使用@SpringBootTest注解
        * 测试方法上加@Test注解
        * 引入RedisDao进行测试。
 

