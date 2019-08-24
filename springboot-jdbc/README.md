# SpringBoot用JdbcTemplates访问Mysql

参考：
https://blog.csdn.net/forezp/article/details/70477821

本文出自方志朋的博客

## 学习记录：

### 个人发现知识

> 利用idea连接MySql数据库：

右侧Database -> 数据库设置按钮(Data Source Properties)
* 设置连接MySql的driverClassName
* 设置连接MySql的用户、数据库名称及地址信息
![mysql_setting.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/mysql_setting.png)
![add_mysql_connect.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/add_mysql_connect.png)

**遇到连接数据库失败的问题。后定位问题为url配置错误**

`修改url为:jdbc:mysql://localhost:3306/db_spring?serverTimezone=GMT`

参考：https://blog.csdn.net/weixin_40584771/article/details/83242729

> 利用idea操作MySql数据库：

右侧Database -> QL控制台按钮(Data Source Properties)
* 步骤一：打开数据库控制台console
* 步骤二：在控制台内编辑sql语句
* 步骤三：选中sql语句，执行
    * 方法一：点击左上角的Execute按钮
    * 方法二：右击，选择列表中的Execute
* 步骤四：执行结果可以在下方控制台看到。

![sql_step1.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/sql_step1.png)
![sql_step2.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/sql_step2.png)
![sql_step3.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/sql_step3.png)
![sql_step4.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-jdbc/mysql/sql_step4.png)

项目分析：
---

> 代码结构分析一：Dao层是和数据库打交道的，Service层会封装具体的业务。有点抽象..
>
> 代码结构分析二：
>> Dao层：主要是做数据持久层的工作，负责与数据库进行联络的一些任务都封装在此，DAO层的设计首先是设计DAO的接口，然后就可在模块中调用此接口来进行数据业务的处理，而不用关心此接口的具体实现类是哪个类，显得结构非常清晰，DAO层的数据源配置
>
>> service层：主要负责业务模块的逻辑应用设计,Service层的业务实现，具体要调用到已定义的DAO层的接口，封装Service层的业务逻辑有利于通用的业务逻辑的独立性和重复利用性，程序显得非常简洁。  

* service层和dao层都有最基础的**接口定义**
* service层和dao层都有实现各自接口的**实现类**
    * 实现类需要用@Service和@Repository注解
    * 在AccountDaoImpl里使用jdbcTemplate执行sql语句来操作数据库
        ```
        @Autowired
        private JdbcTemplate jdbcTemplate;
        ```
* 通过Controller将接口暴露给用户

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter-jdbc
    * 在pom文件引入mysql连接类和连接池：mysql-connector-java、druid
    * 在pom文件引入web服务：spring-boot-starter-web
* application.properties文件配置
    * 配置mysql的驱动类，数据库地址，数据库账号、密码信息
```
通过引入这些依赖和配置一些基本信息，springboot就可以访问数据库类。
```
* 具体编码
    * 创建实体类Account 
    * 编写dao层代码
    * 编写service层代码
    * 构建一组restful api来展示
 

