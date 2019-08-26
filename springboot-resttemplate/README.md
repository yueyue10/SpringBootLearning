# 第十六篇：用restTemplate消费服务

参考：
https://blog.csdn.net/forezp/article/details/71023724

本文出自方志朋的博客

创建一个springboot工程，去消费RESTFUL的服务。这个服务是 http://gturnquist-quoters.cfapps.io/api/random ，它会随机返回Json字符串。

在Spring项目中，它提供了一个非常简便的类，叫RestTemplate，它可以很简便的消费服务。

项目配置
---

* pom文件配置
    * 在pom文件引入spring-web
* 具体编码
    * 在Application中注册一个RestTemplate
    * 在Application中注册一个CommandLineRunner测试restTemplate从http://gturnquist-quoters.cfapps.io/api/random获取的json字符串
      
 

