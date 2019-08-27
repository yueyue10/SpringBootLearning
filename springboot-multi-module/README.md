# 第二十二篇： 创建含有多module的springboot工程

参考：
https://blog.csdn.net/forezp/article/details/71024153
https://www.cnblogs.com/clwydjgs/p/9255046.html

在springboot中如何创建含有多个module的工程，栗子中含有两个 module，一个作为libarary. 工程，另外一个是主工程，调用libary .其中libary jar有一个服务，main工程调用这个服务。

项目配置
---

* pom文件配置
    * 在根工程的pom中配置modules包含子工程，packaging属性为pom 
    * 在libary工程的pom文件引入spring-boot-configuration-processor，packaging属性为jar
    * 在application工程的pom文件引入spring-boot-starter-actuator，引入libary依赖，packaging属性为jar
* 具体编码
    * 编写libary库下的代码：
        * 创建实体类：Service
            * 使用@Component注解注入spring容器中
        * 创建全局配置标识及内容类：DataProperties
            * 使用@ConfigurationProperties("data")注解表示在application.properties中配置的标识
            * 编写String message属性，表示可以在application.properties中配置的标识的属性值
            * 具体配置是放在application工程的application.properties里面
        * 创建提供全局配置的数据类：DataConfiguration
            * 使用@Configuration表示此类是配置类
            * 使用@EnableConfigurationProperties(DataProperties.class)设置配置内容类。
            * 使用@Bean注解的方法提供数据对象。方法参数通过DataProperties可以传入作为数据源。
        * 创建Test测试类，可以进行测试
    * 编写application工程下的代码：
        * 在application.yml中配置全局配置数据
        * 编写Application类里的代码
            * 使用@RestController注解表示该类是一个接口提供类
            * 在application类中使用@Import -DataConfiguration导入全局配置数据提供类,不需要创建对象。
            * 直接使用@Autowired -Service引入service对象，因为在DataConfiguration里已经提供了。
            * 创建home方法提供一个接口，以及返回service数据。
* 测试代码
    * 运行application工程的Application类，在浏览器访问：http://localhost:8080
    * 运行libary库的Test方法进行测试
