# ch3 线程 #

1.重用进程
2.轻量级的线程来处理连接，通过使用线程池而不是为了每个连接生成新线程，服务器每分钟就可以用不到100个线程来处理数千个短连接。

## 运行线程 ##

线程如果以小写字母t大头（thread），这就表示虚拟机中的一个单独、独立的执行路径。如果以大写字母T大头（Thread），则是java.lang.Thread类的一个实例。

### 派生Thread ###

[code1](code/c1/DigestThread.java)

### 实现Runnable接口 ###

[code2](code/c2/DigestRunnable.java)

## 从线程返回信息 ##

[code3](code/c3/ReturnDigestUserInterface.java)

### 竞态条件 ###

[code3](code/c3/ReturnDigestUserInterface.java)

### 轮询 ###

code4

### 回调 ###

code5

### Future、Callable和Executor ###

code6

## 同步 ##

### 同步块 ###

### 同步方法 ###

### 同步的代替方法 ###

## 死锁 ##

## 线程调度 ##

### 优先级 ###

### 抢占 ###

### 阻塞 ###

### 放弃 ###

### 休眠 ###

### 连接线程 ###

### 等待一个对象 ###

### 结束 ###

## 线程池和Executor ##

java.util.concurrent钟的Executors类，可以非常容易地建立线程池。