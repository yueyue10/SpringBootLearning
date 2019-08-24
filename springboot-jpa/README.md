# 第四篇：SpringBoot 整合JPA

参考：
https://blog.csdn.net/forezp/article/details/70545038
https://www.cnblogs.com/xuwenjin/p/8830850.html

本文出自方志朋的博客

**JPA全称Java Persistence API.JPA通过JDK 5.0注解或XML描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。**

JPA 的目标之一是制定一个可以由很多供应商实现的API，并且开发人员可以编码来实现该API，而不是使用私有供应商特有的API。

JPA是需要Provider来实现其功能的，**Hibernate**就是JPA Provider中很强的一个，应该说无人能出其右。从功能上来说，JPA就是Hibernate功能的一个子集。

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter-data-jpa
    * 在pom文件引入mysql连接类和连接池类：mysql-connector-java
    * 在pom文件引入web服务：spring-boot-starter-web
* application.yml文件配置
    * 配置mysql的驱动类，数据库地址，数据库账号、密码信息
    * 配置jpa属性。_如果通过jpa在数据库中建表，将jpa.hibernate,ddl-auto改为create，建完表之后，要改为update,要不然每次重启工程会删除表并新建。_
* 具体编码
    * 创建实体类Account
        * @Entity 表明是一个映射的实体类
        * @Table(name = "`account-new`")**数据库名字包含特殊字符需要用``包括**
        * @Id表明id， @GeneratedValue 字段自动生成 
    * 编写dao层代码
        * 继承自JpaRepository
    * 编写service层代码
    * 构建一组restful api来展示
 

