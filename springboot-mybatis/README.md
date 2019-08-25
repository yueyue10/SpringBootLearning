# 第六篇：springboot整合mybatis

参考：
https://blog.csdn.net/forezp/article/details/70768477

本文出自方志朋的博客

在springboot下整合mybatis，并访问数据库。

项目配置
---

* pom文件配置
    * 在pom文件引入mybatis-spring-boot-starter
    * 在pom文件引入mysql连接类和连接池类：mysql-connector-java、druid
    * 在pom文件引入web服务：spring-boot-starter-web
* application.properties文件配置
    * 配置mysql的驱动类，数据库地址，数据库账号、密码信息
* 具体编码
    * 创建实体类Account
    * 编写dao层代码
        * 编写AccountMapper接口即可，不需要实现类
        * 给AccountMapper添加@Mapper注解
        * 给方法加上@Insert("sql")注解,方法参数加上@Param("xxx")注解
    * 编写service层代码
    * 构建一组restful api来展示
 

