# SpringBoot用JdbcTemplates访问Mysql

参考：
https://blog.csdn.net/forezp/article/details/70477821

本文出自方志朋的博客

## 项目介绍：

一、项目结构分析
---

> Dao层是和数据库打交道的，Service层会封装具体的业务。有点抽象..
>
> Dao层主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此，DAO层的设计首先是设计DAO的接口，然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰，DAO层的数据源配置
>
> service层：主要负责业务模块的逻辑应用设计,Service层的业务实现，具体要调用到已定义的DAO层的接口，封装Service层的业务逻辑有利于通用的业务逻辑的独立性和重复利用性，程序显得非常简洁。  

* service层和dao层都有最基础的**接口定义**
* service层和dao层都有实现各自接口的**实现类**
    * 实现类需要用@Service和@Repository注解
    * 在AccountDaoImpl里使用jdbcTemplate执行sql语句来操作数据库
        ```
        @Autowired
        private JdbcTemplate jdbcTemplate;
        ```
* 通过Controller将接口暴露给用户

#### 总结来讲
* controller层是对外暴露的
* service层是接口定义类 -> 是对controller的功能及逻辑的实现。
* dao层是数据库操作层 -> 是对service的内部实现。

二、项目配置
---

#### 1.项目pom和application.properties配置
* pom文件配置
    * 在pom文件引入spring-boot-starter-jdbc
    * 在pom文件引入mysql连接类和连接池：mysql-connector-java、druid
    * 在pom文件引入web服务：spring-boot-starter-web
* application.properties文件配置
    * 配置mysql的驱动类，数据库地址，数据库账号、密码信息
```
通过引入这些依赖和配置一些基本信息，springboot就可以访问数据库类。
```

#### 2.具体编码
* 创建实体类Account 
* 编写dao层代码
* 编写service层代码
* 编写controller层代码
* 构建一组restful api来展示
    
三、启动服务，测试各接口
---

| 接口名称               |  REST文件            |        接口地址           |
| --------              |  :----:             |        :----:             |
| 获取account列表接口    |  [account_list](rest/account_list.http)  |GET http://localhost:8080/account/list             |
| 根据id获取account的接口	|  [account_get](rest/account_get.http)  | GET http://localhost:8080/account/1             |
| 添加account接口	    |  [account_add](rest/account_add.http)  | POST http://localhost:8080/account?name=asdf&money=322            |
| 更新account接口	    |  [account_list](rest/account_update.http)  | PUT http://localhost:8080/account/1?name=yyy&money=12           |
| 删除account接口	    |  [account_delete](rest/account_delete.http)  | GET http://localhost:8080/account/delete?id=4           |

四、个人发现知识
---

#### 利用Idea开发工具连接MySql数据库：

位置：右侧Database -> 数据库设置按钮(Data Source Properties)

#### 1.配置MySql数据库链接

* 设置连接MySql的driverClassName
* 设置连接MySql的用户、数据库名称及地址信息
![mysql_setting.png](mysql/mysql_setting.jpg)
![add_mysql_connect.png](mysql/add_mysql_connect.jpg)

___遇到连接数据库失败的问题。后定位问题为url配置错误___

`修改url为:jdbc:mysql://localhost:3306/db_spring?serverTimezone=GMT`

参考：https://blog.csdn.net/weixin_40584771/article/details/83242729

#### 2.利用idea操作MySql数据库

位置：右侧Database -> QL控制台按钮(Data Source Properties)

>步骤一：打开数据库控制台console

![sql_step1.jpg](mysql/sql_step1.jpg)

>步骤二：在控制台内编辑sql语句

![sql_step2.jpg](mysql/sql_step2.jpg)

>步骤三：选中sql语句，执行
* 方法一：点击左上角的Execute按钮
* 方法二：右击，选择列表中的Execute

![sql_step3.jpg](mysql/sql_step3.jpg)

> 步骤四：执行结果可以在下方控制台看到。

![sql_step4.jpg](mysql/sql_step4.jpg)

 

