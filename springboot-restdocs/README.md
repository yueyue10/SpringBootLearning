# 第十篇： 用spring Restdocs创建API文档

参考：
https://blog.csdn.net/forezp/article/details/71023510

本文出自方志朋的博客

用spring官方推荐的restdoc去生成api文档。

此工程通过 JUnit单元测试和Spring的MockMVC就可以生成接口文档，将http接口通过Api文档暴露出来。

项目配置
---

* pom文件配置
    * 在pom文件引入spring-restdocs-mockmvc
    * 在pom文件引入web服务：spring-boot-starter-web
    * 在pom文件引入web服务：spring-boot-starter-test
* 具体编码
    * 创建一个controller类:HomeController
    * 创建一个单元测试类:WebLayerTest
        * 使用@AutoConfigureRestDocs注解设置输出路径
            ```
            @AutoConfigureRestDocs(outputDir = "target/snippets")
            ```
        * 启动单元测试类后就可以在target/snippets下生成adoc文件
    * 创建文件：src/main/asciidoc/index.adoc，编写接口文档输出样式。
        * pom中使用asciidoctor-maven-plugin插件用于编辑adoc文件，可以去掉。
* 在当前项目路径下打开cmd命令，使用`mvnw package`命令生成接口文档。
    * 生成的接口文档路径为：/target/generated-docs/index.html       
 

