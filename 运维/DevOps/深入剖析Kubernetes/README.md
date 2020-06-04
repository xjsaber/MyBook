# 深入剖析Kubernetes #

## 课前必读 ##

## 开篇词 | 打通“容器技术”的任督二脉 ##

## 01 | 预习篇.小鲸鱼大事记（一）：初出茅庐 ##

## 02 | 预习篇.小鲸鱼大事记（二）：崭露头角 ##

## 03 | 预习篇.小鲸鱼大事记（二）：群雄并起 ##

## 04 | 预习篇.小鲸鱼大事记（二）：尘埃落定 ##

## 容器技术概念入门篇 ##

## 05 | 白话容器基础（一）：从进程说开去 ##

容器技术的核心功能，就是通过约束和修改进程的动态表现，从而为其创造出一个“边界”。Cgroups技术是用来制造约束的主要手段，而Namespace技术则是用来修改进程视图的主要方法。

	docker run -it busybox /bin/sh

使docker内的程序看不到它前面的其他进程，对被隔离应用的进程空间做了手脚，使得这些进程只能看到重新计算过的进程编号，比如PID=1。可实际上，它在宿主机的操作系统里还是原来第100进程。（Namespace）

	int pid = clone(main-function, stack_size, SIGCHLD, NULL); 系统调用就会为我们创建一个新的进程，并且返回它的进程号pid。

我们还可以多次执行上面的clone()，创建多个PID Namespace，而每个Namespace里的应用进程，都会认为自己是当前容器里的第1号进程，他们即看不到宿主机里真正的进程空间，也看不到其他PID Namespace里的具体情况。

还有其他Namespace，

所以说，容器，其实是一种特殊的进程而已

### 总结 ###

## 06 | 白话容器基础（二）：隔离与限制 ##

Namespace技术实际上修改了应用进程看待整个计算机“视图”，即它的“视线”被操作系统做了限制，只能“看到”某些指定的内容。（但对于宿主机来说）

有利有弊，基于Linux Namespace的隔离机制相比于虚拟化技术可能会出现：隔离不彻底

1. 既然容器只是运行在宿主机上的一种特殊的进程，那么多个容器之间使用的就还是同一个宿主机的操作系统内核。
2. 在Linux内核中，有很多资源和对象是不呢功能被Namespace化的，最典型的例子就是：时间。

## 07 | 白话容器基础（三）：深入理解容器镜像 ##

Namespace和Cgroups

实际上，Mount Namespace正是基于对chroot的不断改良才被发明出来的，它也是Linux操作系统里的第一个Namespace。

需要明确的是，rootfs知识一个操作系统所包含的文件、配置和目录，并不

## Kuberneters容器运行时 ##

## 总结 ##

## 08 | 白话容器基础（四）：重新认识Docker容器 ##

## 09 | 从容器到容器云：谈谈Kubernetes的本质 ##

## 10 | Kubernetes一键部署利器：kubeadm ##

要真正发挥容器技术的实力，就不能仅仅局限于对Linux容器本身的钻研和使用。

如何使用这些技术来“容器化”你的应用

Kubernetes的功能那么多，这样一键部署出来的集群，能用于生产环境吗？

### kubeadm的工作原理 ###

## 13 | 为什么我们需要Pod？ ##

Pod，是Kubernetes项目中最小的API对象。Pod，是Kubernetes项目的原子调度单位。

Namespace做隔离，Cgroups做限制，rootfs做文件系统。为什么Kubernetes项目又突然搞出一个Pod来呢？

容器的本质是进程。

已知rsyslogd由三个进程组成：一个imklog模块，一个imuxsock模块，一个rsyslogd自己的main函数主进程。这三个进程一定要运行再同一个机器上。

容器的“单进程模型”，并不是指容器里职能进行“一个”进程，而是指容器没有管理多个进程的能力。这是因为容器里PID=1的进程就是应用本身，其他的进程都是这个PID=1进程的子进程。

	docker run main 
	docuer run imklog
	docker run imuxsock

Pod是Kubernetes里的原子调度单位。Kubernetes项目的调度器，是统一按照Pod而非容器的资源需求进行计算的。

容器设计模式，Pod的实现原理

1. 关于Pod最重要的一个事实是：它知识一个逻辑概念

Pod里的所有容器，共享的是同一个Network Namspace，并且可以声明共享同一个Volume。

对于Pod里的容器A和容器B来说：

* 可以直接使用localhost进行通信；
* 看到的网络设备跟Infra容器看到的完全一样；
* 一个Pod只有一个IP地址，也就这个Pod的Network Namespace对应的IP地址；
* 其他的网络资源，都是一个Pod一份，并且被该Pod中的所有容器共享；
* Pod的生命周期只跟Infra容器一致，而与容器A和B无关。

将来如果你要为Kubernetes开发一个网络插件时，应该重点考虑的是如何配置这个Pod的Network Namespace，而不是每一个用户容器如何使用你的网络配置，这是没有意义的。

## 45 | 幕后英雄：SIG-Node与CRI ##

