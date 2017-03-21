# ch3 线程 #

1.重用进程
2.轻量级的线程来处理连接，通过使用线程池而不是为了每个连接生成新线程，服务器每分钟就可以用不到100个线程来处理数千个短连接。

### 运行线程 ###

线程如果以小写字母t大头（thread），这就表示虚拟机中的一个单独、独立的执行路径。如果以大写字母T大头（Thread），则是java.lang.Thread类的一个实例。

### 派生Thread ###

[code1](code/c1/DigestThread.java)

### 实现Runnable接口 ###

[code2](code/c2/DigestRunnable.java)

### 从线程返回信息 ###

[code3](code/c3/ReturnDigestUserInterface.java)

### 竞态条件 ###

[code3](code/c3/ReturnDigestUserInterface.java)

### 轮询 ###