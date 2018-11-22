# polymerization
zero to  one


# 用于基础框架搭建学习，网络资源利用
1.新技术孵化，验证，团队建设

2.采用混合开发，rxjava rxandroid ,kotlin,响应编程

3.框架整体采用mvp，module 采用mvvm databinding等其他新技术


功能封装进度


11.12 1.组件化搭建

   1>过程中:gradle 依赖无法下载
   解决：更改gradle版本为可以依赖的版本，默认新建demo的gradle版本为最新版本。
   2> 项目的主题找不到
   解决：更改supportV7依赖,项目主题包含在v7中

   tips: android 主题的各种玩法 http://www.cnblogs.com/zhouyou96/p/5323138.html




 1.13  2.依赖增加 RxJava,RxAndroid,OkHttp

   2> support v4 版本不一致问题
   解决:设置例外不小，更改低版本的v4

   2.2> 增加gons ，增加mutidex，增加 签名配置

   tips：签名配置需要新建一个空文件，然后对空文件进行签名生成。



   待封装：cookie,token,公共参数，基类封装，工具类


  11.14  day3

     3.1 增加cookiejar 处理
     3.2 增加Sharepreferce 工具类
     3.3 增加 webview widget

  11.15   day4

     调试Arouter。
     中间卡了好久，问题是baseFragment 竟然没有继承Fragment，导致在Fragment上的Route 注解的没法识别，呃哈哈哈


 11.21

     增加公共弹窗，封装加载框到网络请求中。绑定activity

 11.22

     订阅手动解绑<实现方式比较low>

     自动解绑没有找到的合适的入口 RxlifeCycle 2

