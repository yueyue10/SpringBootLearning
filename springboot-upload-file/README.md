# 第十七篇：上传文件

参考：
https://blog.csdn.net/forezp/article/details/71023752

本文出自方志朋的博客

在springboot工程作为服务器，去接收通过http 上传的multi-file的文件。

> 其他相关知识：
>> springboot如何使用velocity模版引擎
>>
>> 参考:https://blog.csdn.net/zhangxing52077/article/details/73194948
>>
>> Spring中Model详解
>> 参考:https://blog.csdn.net/yongwa123/article/details/85017551

项目配置
---

* pom文件配置
    * 在pom文件引入spring-boot-starter-thymeleaf
    * 在pom文件引入web服务：spring-boot-starter-web
    `为例能够上传文件在服务器，你需要在web.xml中加入标签做相关的配置，但在sringboot 工程中，它已经为你自动做了，所以不需要你做任何的配置。`
* 在resources/templates下添加upload_page.html文件
    * 使用form标签提供上传文件和提交功能
    * 使用ul-li标签提供文件列表展示功能
    * 在标签中使用Model层数据需要加`th:`前缀
    * 使用**file_list数据**，先遍历再使用
* application.properties文件配置
    * 配置上传文件大小：spring.http.multipart.max-file-size=128KB
* 具体代码
    * 编写Service层代码
        * 编辑StorageService接口
        * 编写接口实现类FileSystemStorageService：
            * 实现文件操作的具体逻辑
            * 初始化的时候配置文件保存路径rootLocation
    * 编写实体类等代码
        * 编写文件配置类StorageProperties
            * 使用@ConfigurationProperties("storage")注解标识该类为配置类
    * 编写controller层代码
        * 编写FileUploadController类提供外部接口
            * 编写listUploadedFiles()方法
                * 提供**file_list数据**：文件的Http响应数据集合
                    * 具体先通过storageService.loadAll()得到所有文件路径Path的Stream流
                    * 再通过Stream的map->serveFile 将Path数据处理成Http响应数据
                    * 再通过Stream的collect方法将数据处理成数据集合
                * 提供文件操作html页面配置(~~猜测应该是传入Model后识别到该方法功能的~~)
            * 编写handleFileUpload()方法
                * 实现文件上传的业务逻辑
                    * 保存文件到本地
                    * 刷新界面并提供message信息
                * 实现html中"上传"按钮点击之后的逻辑(~~猜测是@Controller类注解、@PostMapping("/")方法注解、 return "redirect:/";共同影响所致。~~)
    * 编写Application代码
        * 使用注解@EnableConfigurationProperties(StorageProperties.class)，使用配置类
        * 注册CommandLineRunner类，在run方法里面删除文件及创建文件夹
* 启动工程，访问：http://localhost:8080/进行测试

 
>参考文档：
- [Files.walk方法][1]
- [Path.relativize方法][2]
- [ResponseEntity介绍][3] [、使用spring ResponseEntity处理http响应][4]
- [Java 8 Stream][5]
- [Web MVC 框架之URI Builder-MvcUriComponentsBuilder][6]
- [@RequestMapping正则][7.1] [、正则表达式 - 语法][7.2]
- [HTML标签][8]
- [Thymeleaf教程][9]

[1]:https://www.mkyong.com/java/java-files-walk-examples/
[2]:https://docs.oracle.com/javase/9/docs/api/java/nio/file/Path.html
[3]:https://www.jdon.com/springboot/responseentity.html
[4]:https://blog.csdn.net/neweastsun/article/details/81142870
[5]:https://www.runoob.com/java/java8-streams.html
[6]:https://www.cnblogs.com/larryzeal/p/6131664.html
[7.1]:https://www.cnblogs.com/yanghongfei/p/6944161.html
[7.2]:https://www.runoob.com/regexp/regexp-syntax.html
[8]:https://www.w3school.com.cn/tags/tag_li.asp
[9]:https://www.e-learn.cn/thymeleaf
