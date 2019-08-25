# 第十一篇：springboot集成swagger2

参考：
https://blog.csdn.net/forezp/article/details/71023536

本文出自方志朋的博客

swagger,中文“拽”的意思。它是一个功能强大的api框架，它的集成非常简单，不仅提供了在线文档的查阅，而且还提供了在线文档的测试。
另外swagger很容易构建restful风格的api，

项目配置
---

* pom文件配置
    * 在pom文件引入springfox-swagger2、springfox-swagger-ui
    * 在pom文件引入web服务：spring-boot-starter-web
* Swagger2配置文件
    * 通过@Configuration注解，表明它是一个配置类，
    * @EnableSwagger2开启swagger2
    * apiINfo()配置一些基本的信息
    * apis()指定扫描的包会生成文档。
* 对Controller进行配置接口描述
    * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。
    * @Api：修饰整个类，描述Controller的作用
    * @ApiOperation：描述一个类的一个方法，或者说一个接口
    * @ApiParam：单个参数描述
    * @ApiModel：用对象来接收参数
    * @ApiProperty：用对象接收参数时，描述对象的一个字段
    * @ApiResponse：HTTP响应其中1个描述
    * @ApiResponses：HTTP响应整体描述
    * @ApiIgnore：使用该注解忽略这个API
    * @ApiError ：发生错误返回的信息
    * @ApiParamImplicitL：一个请求参数
    * @ApiParamsImplicit 多个请求参数
    * 如果你不需要某接口生成文档，只需要在加@ApiIgnore注解即可。
* 启动工程，访问：http://localhost:8080/swagger-ui.html ，就看到swagger-ui:

 

