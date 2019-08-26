# 第二十篇： 处理表单提交

参考：
https://blog.csdn.net/forezp/article/details/71023868

本文出自方志朋的博客

通过springboot 去创建和提交一个表单。

使用过程
---

* 在pom文件引入spring-boot-starter-thymeleaf环境依赖
* 代码实现
    * 创建实体bean：Greeting  
    * 创建Controller类：GreetingController 
* 在resources/templates创建html界面
    * 编写greeting.html
    * 编写result.html
    
* 启动工程，访问http://localhost:8080/greeting 进行测试

