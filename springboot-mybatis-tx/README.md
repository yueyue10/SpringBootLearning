# 第七篇：springboot开启声明式事务

参考：
https://blog.csdn.net/forezp/article/details/70833629

本文出自方志朋的博客

springboot开启事务很简单，只需要一个注解@Transactional 就可以了。

因为在springboot中已经默认对jpa、jdbc、mybatis开启了事事务，引入它们依赖的时候，事物就默认开启。
当然，如果你需要用其他的orm，比如beatlsql，就需要自己配置相关的事物管理器。

项目配置
---

* pom文件配置
    * 在pom文件引入mybatis-spring-boot-starter
    * 在pom文件引入mysql连接类和连接池类：mysql-connector-java、druid
    * 在pom文件引入web服务：spring-boot-starter-web
* application.yml文件配置
    * 配置mysql的驱动类，数据库地址，数据库账号、密码信息
    * 配置mybatis。
        * 通过配置mybatis.mapper-locations来指明mapper的xml文件存放位置
            ```
            mybatis.mapper-locations=classpath*:mybatis/*Mapper.xml
            ```
        * mybatis.type-aliases-package来指明和数据库映射的实体的所在包
            ```
            mybatis.type-aliases-package=com.forezp.entity
            ```
> 经过以上步骤，springboot就可以通过mybatis访问数据库来。
* 具体编码
    * 创建实体类Account
    * 编写dao层代码
        * 创建使用@Mapper注解的AccountMapper接口，接口方法使用@Insert("sql")注解，方法参数使用@Param("name")注解
        * 创建自定义AccountMapper2接口，定义update方法。
            * 在resources/mybatis目录下创建AccountMapper.xml编写接口实现代码
                * 使用mapper-namespace定义实现的接口
                * 使用update或者其他操作sql的标签实现接口里面的方法，使用id对应方法名
    * 编写service层代码
        * 在AccountService2中对方法使用 **@Transactional注解** 的作用：*开启事务-只有方法中没有异常时才会操作数据库，否则不会操作数据库*。
    * 构建一组restful api来展示
 

