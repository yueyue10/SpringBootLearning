# 第十八篇： 定时任务（Scheduling Tasks）

参考：
https://blog.csdn.net/forezp/article/details/71023783

本文出自方志朋的博客

通过spring去做调度任务。

项目配置
---

* 创建一个Springboot工程
* 具体编码
    * 在Application中加上@EnableScheduling注解，开启调度任务。
    * 创建定时任务ScheduledTasks：
        * 使用@Component注解将此类放到Spring容器中
        * 通过在方法上加@Scheduled注解，表明该方法是一个调度任务
            ```
            @Scheduled(fixedRate = 5000) ：上一次开始执行时间点之后5秒再执行
            @Scheduled(fixedDelay = 5000) ：上一次执行完毕时间点之后5秒再执行
            @Scheduled(initialDelay=1000, fixedRate=5000) ：第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
            @Scheduled(cron=" /5 ") ：通过cron表达式定义规则，什么是cro表达式，自行搜索引擎。
            ```
* 启动springboot工程，控制台每过5s就打印出了当前的时间。

```
2019-08-26 17:43:28.896  INFO 7696 --- [           main] c.f.SpringbootSchedulingTasksApplication : Started SpringbootSchedulingTasksApplication in 1.732 seconds (JVM running for 2.871)
2019-08-26 17:43:33.870  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:33
2019-08-26 17:43:38.870  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:38
2019-08-26 17:43:43.870  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:43
2019-08-26 17:43:48.869  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:48
2019-08-26 17:43:53.870  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:53
2019-08-26 17:43:58.869  INFO 7696 --- [pool-1-thread-1] com.forezp.task.ScheduledTasks           : The time is now 17:43:58
```

总结
---

在springboot创建定时任务比较简单，只需2步：

* 1.在程序的入口加上@EnableScheduling注解。
* 2.在定时方法上加@Scheduled注解。

