# 第十三篇：springboot集成spring cache

参考：
https://blog.csdn.net/forezp/article/details/71023579
https://blog.csdn.net/zl_momomo/article/details/80403564
https://www.cnblogs.com/lyjing/p/8427832.html
https://www.cnblogs.com/myblogs-miller/p/9046425.html

本文出自方志朋的博客

**声明式缓存**

Spring 定义 CacheManager 和 Cache 接口用来统一不同的缓存技术。例如 JCache、 EhCache、 Hazelcast、 Guava、 Redis 等。在使用 Spring 集成 Cache 的时候，我们需要注册实现的 CacheManager 的 Bean。

Spring Boot 为我们自动配置了 JcacheCacheConfiguration、 EhCacheCacheConfiguration、HazelcastCacheConfiguration、GuavaCacheConfiguration、RedisCacheConfiguration、SimpleCacheConfiguration 等。

**默认使用 ConcurrenMapCacheManager**

在我们不使用其他第三方缓存依赖的时候，springboot自动采用ConcurrenMapCacheManager作为缓存管理器。

使用过程
---

* 在pom文件引入spring-boot-starter-cache环境依赖
* 代码实现
    * 创建实体bean：Book 
    * 编写dao层代码
        * 创建一个数据访问接口:BookRepository 
        * 创建接口实现类：SimpleBookRepository
            * 使用@Component注解的目的是把这些类纳入进spring容器中管理。
            * 使用线程的延迟操作模拟操作mysql、nosql等等
            * 给需要缓存的方法加上@Cacheable("book")注解
    * 编写测试类AppRunner
        * 使用@Component注解
        * 实现在CommandLineRunner接口，让run方法里面的测试代码在项目启动后立即执行。
    * 在程序的入口中加入@EnableCaching开启缓存技术：
        * 在Application类加入该注解
```
在需要缓存的地方加入@Cacheable注解，比如在getByIsbn（）方法上加入@Cacheable(“books”)，这个方法就开启了缓存策略，当缓存有这个数据的时候，会直接返回数据，不会等待去查询数据库。
``` 
> 运行程序的结果如下：
```
2019-08-25 18:21:40  INFO 2196 --- [main] forezp.AppRunner   : .... Fetching books
2019-08-25 18:21:43  INFO 2196 --- [main] forezp.AppRunner   : isbn-1234 -->Book{isbn='isbn-1234', title='flag=482创建的book'}
2019-08-25 18:21:46  INFO 2196 --- [main] forezp.AppRunner   : isbn-4567 -->Book{isbn='isbn-4567', title='flag=141创建的book'}
2019-08-25 18:21:46  INFO 2196 --- [main] forezp.AppRunner   : isbn-1234 -->Book{isbn='isbn-1234', title='flag=482创建的book'}
2019-08-25 18:21:46  INFO 2196 --- [main] forezp.AppRunner   : isbn-4567 -->Book{isbn='isbn-4567', title='flag=141创建的book'}
2019-08-25 18:21:46  INFO 2196 --- [main] forezp.AppRunner   : isbn-1234 -->Book{isbn='isbn-1234', title='flag=482创建的book'}
2019-08-25 18:21:46  INFO 2196 --- [main] forezp.AppRunner   : isbn-1234 -->Book{isbn='isbn-1234', title='flag=482创建的book'}
```
通过左侧的时间和Book对象里面的flag标签可以看到从第三项开始的Book数据是从缓存中取到的。
