# 第十五篇：Springboot整合RabbitMQ

参考：
https://blog.csdn.net/forezp/article/details/70545038
https://www.cnblogs.com/xuwenjin/p/8830850.html

本文出自方志朋的博客

该项目就是整合RabbitMQ服务器，并且通过它怎么去发送和接收消息。
我将构建一个springboot工程，通过RabbitTemplate去通过MessageListenerAdapter去订阅一个POJO类型的消息。

项目配置
---

* 安装rabbitmq，开启rabbitmq服务
* pom文件配置
    * 在pom文件引入加上spring-boot-starter-amqp
* 具体编码
    * 创建消息接收者Receiver 
        * 使用@Component注解加入spring容器
        * 使用CountDownLatch用于告诉发送者消息已经收到了。只需要latch.countDown()就可以。
    * 在Application类里面提供Rabbitmq需要的组件对象。
        * 提供Queue 和TopicExchange ，并进行绑定。
        * 提供一个消息监听容器。
    * 创建测试类Runner 
        * 实现CommandLineRunner接口
        * 使用@Component注解加入spring容器
        * 在run方法中进行测试
       
> 在spring程序中，RabbitTemplate提供了发送消息和接收消息的所有方法。你只需简单的配置下就行了：
> 
> * 需要一个消息监听容器
> * 声明一个quene,一个exchange,并且绑定它们
> * 一个组件去发送消息
