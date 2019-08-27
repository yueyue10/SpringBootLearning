# 第二十三篇： 异步方法

参考：
https://blog.csdn.net/forezp/article/details/71024169
https://blog.csdn.net/wei_lei/article/details/74262818
https://blog.csdn.net/u014209205/article/details/80598209
https://blog.csdn.net/fenglllle/article/details/84473345

本文出自方志朋的博客

在springboot 使用异步方法，去请求github api

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter
    * 在pom文件引入spring-web
    * 在pom文件引入jackson-databind
* 具体编码
    * 创建实体类User 
        * 使用@JsonIgnoreProperties(ignoreUnknown=true)表示忽略属性错误
    * 编写Service类：GitHubLookupService
        * 创建RestTemplate对象提供请求数据功能
        * 创建findUser方法实现异步请求github数据
        * 给方法加上@Async注解表示该方法是异步方法
    * 编写测试类AppRunner
        * 使用@Component注解将此类放到spring容器中
        * 实现CommandLineRunner接口，在程序启动后就执行类中的run方法
        * 在run方法里面使用GitHubLookupService请求github数据
    * 在Application类里面配置异步任务参数
        * 使用@EnableAsync开启异步任务
        * 继承AsyncConfigurerSupport类在getAsyncExecutor方法里面配置异步任务参数
* 启动项目进行测试async的效果
       


