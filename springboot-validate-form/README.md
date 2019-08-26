# 第十九篇： 验证表单信息

参考：
https://blog.csdn.net/forezp/article/details/71023817

本文出自方志朋的博客

在springboot中验证表单信息。
在springmvc工程中，需要检查表单信息，表单信息验证主要通过注解的形式。

项目配置
---

* pom文件配置
    * 在pom文件引入web、thymeleaf、validator、el
* 具体编码
    * 创建实体类PersonForm
        * 给name添加@NotNull、@Size(min=2, max=30)验证条件：name的长度为2-30个字符
        * 给age添加@NotNull、@Min(18)验证条件：age不能小于18
    * 编写WebController类
        * 继承自WebMvcConfigurerAdapter
        * 实现addViewControllers()方法。(~~作用暂不清楚~~)
        * 创建showForm()和checkPersonInfo()方法。
* 创建form表单文件
    * form.html提交界面
    * results.html结果界面
 * 启动工程，访问http://localhost:8080 进行测试。
       


