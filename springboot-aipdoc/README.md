# 第十二篇：springboot集成apidoc

参考：
https://blog.csdn.net/forezp/article/details/71023579

本文出自方志朋的博客

apidoc是基于注释来生成文档的，它不基于任何框架，而且支持大多数编程语言。

apidoc简介：
---
apidoc通过在你代码的注释来生成api文档的。它对代码没有侵入性，只需要你写好相关的注释即可，并且它仅通过写简单的配置就可以生成高颜值的api接口页面。它基于node.js，所以你需要安装node.js环境。

使用过程
---

* 通过命令安装apidoc：
> npm install apidoc -g
  
* 在项目的主目录新建一个apidoc.json文件配置apidoc
* 在UserController里面编写接口注释:
    ```
    /**
     * @api {POST} /users/:id 修改(完善)用户信息
     * @apiGroup Users
     * @apiVersion 0.0.1
     * @apiDescription 修改(完善)用户信息
     * @apiParam (200) {String} [name] 真实姓名
     * @apiParam (200) {String} [mobile] 手机号
     * @apiParam (200) {String} [birthday] 生日
     * @apiParam (200) {String} [email] 邮箱
     * @apiParam (200) {String} [summary] 简介
     * @apiParam (200) {String} [idCardNo] 身份证号
     * @apiParam (200) {String} [address] 家庭住址
     * @apiSuccess (200) {String} msg 信息
     * @apiSuccess (200) {int} code 0 代表无错误 1代表有错误
     * @apiSuccessExample {json} 返回样例:{"code":"0","msg":"修改成功"}
     */
    ```
 * 在工程的外层目录新建文件夹apidoc
 * 在工程的外层目录运行apidoc命令apidoc -i springboot-aipdoc/ -o apidoc/
    * -i 输入目录 -o 输出目录


