# 第二十四篇： springboot整合docker

参考：

https://blog.csdn.net/forezp/article/details/71024219

https://www.runoob.com/docker/docker-tutorial.html

https://www.cnblogs.com/hei12138/p/ideausedocker.html

~~和上面的类似：https://www.cnblogs.com/shamo89/p/9201513.html~~

本文出自方志朋的博客

### 个人学习知识：

电脑配置是win10家庭版，所以安装了docker toolbox来使用Docker，和docker for window效果应该是一样的。
- docker-toolbox的配置可参考[这里][docker-toolbox-config]
- 启动docker通过执行上面安装好的Docker QuickStart程序
- 使用docker run -p 8090:8080/tcp my_image:latest命令映射8090端口到本地：
    - 出现问题：容器启动成功，web服务启动成功。但是不能使用本地ip:127.0.0.1:8090访问，只能使用http://192.168.99.100:8090/访问
- 使用idea工具连接Docker后操作很方便，尤其是对docker不熟悉时需要经常使用创建镜像、创建容器、删除镜像、删除容器使用命令行太麻烦，用idea这个工具就很方便了、
    - 使用idea的docker工具还可以修改容器的一些配置，包括名字等。修改后需要点save按钮进行保存。

![idea_docker_connect.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-with-docker/doc/idea_docker_connect.png)
![idea_docker_tools.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-with-docker/doc/idea_docker_ui.png)

## 学习记录：

* 在原项目的基础上，参考[这篇文档][docker-SpringBoot]进行修改后使用：
    * 修改Dockerfile文件：具体区别是FROM的镜像不同，RUN的命令不同，ENTRYPOINT命令里面不同
    * 这里我把EXPOSE命令注释掉了，因为启动容器一直找不到服务地址所以就去掉了
    * docker-maven-plugin那里的配置不需要修改，都一样。需要在properties里面指定docker.image.prefix，即镜像名称。
* 两种方式将web服务打包成docker镜像文件，参考还是[这篇文档][docker-SpringBoot]。
    * 第一种方式：使用Dockerfile文件里面出现的{Run on Docker}按钮
        * 使用mvn clean package命令先生成服务的jar包
        * 将jar包放在和Dockerfile同目录下
        * 配置生成的image镜像属性及container容器属性
        ![idea_docker_dockerfile_config.png](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-with-docker/doc/idea_docker_dockerfile_config.png)
            * 进入Dockerfile文件的运行配置方法如下：
            * 方法一、可以右击{Run on Docker}按钮，点击{Edit 'docker/Dockerfile'...}进行配置
            * 方法二、点击idea工具栏里面的{Edit Configurations...}按钮，选中 Docker > {docker/Dockerfile}进行配置
            * 在出现的{Edit Run Configuration}里面可以配置image-tag、Container-name等。
        * `直接点击{Run on Docker}按钮可以打包镜像并且开启容器`
        * 右击{Run on Docker}按钮，可以选择单独打包镜像。
    * 第二中方式：使用docker-maven-plugin插件，打包成镜像
        * 在pom.xml中配置docker-maven-plugin插件
        * 使用`mvn clean package -DskipTests=true docker:build`命令，打包项目并构建镜像
        * 可以使用docker images命令或者在idea的docker插件里面看到新生成的镜像文件：forezp/springboot-with-docker:latest
* 两种方式使用上面生成的docker镜像文件开启容器
    * 第一种方法：使用idea的docker工具开启容器
        * 第一步：右击需要使用的image镜像，点击{Create Container}-{Crate...}
        ![开启容器](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-with-docker/doc/idea_docker_create_container.png)
        * 第二步：配置生成的容器的参数:包括容器名称，映射端口等
        ![容器配置](https://raw.githubusercontent.com/yueyue10/SpringBootLearning/master/springboot-with-docker/doc/idea_docker_container_config.png)
        * 第三步：将容器配置好后，可以直接在Idea的工具栏的Run configuration里面选中{Docker Image}，点击{Run}按钮运行即可开启容器。
        * 第三步：上面的操作也可以换成这种，右击需要使用的image镜像，点击{Create Container}-{Docker Image}即可开启容器。
    * 第二种方法：使用docker命令开启docker容器
        * 使用docker run -p 9090:8080/tcp [my_image:latest],my_image:latest-就是镜像Tag标签
* 启动容器后可以测试web服务：
    * 启动容器后可以看到Spring服务运行情况，没有问题就可以测试web服务
    * 访问：http://192.168.99.100:8090/
    
[docker-SpringBoot]:https://www.cnblogs.com/hei12138/p/ideausedocker.html
[docker-toolbox-config]:https://www.cnblogs.com/chongyao/p/9078202.html
