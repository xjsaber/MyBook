# Flink实战 #

[学习链接](http://www.54tianzhisheng.cn/2018/10/13/flink-introduction/)

#### Flink程序与数据流结构 ####

	DataStream<String> lines = env.addSrouce

## 准备工作 ##

centos75

	wget flink-1.10.0-bin-scala_2.12.tgz
	tar -zxvf flink-1.10.0-bin-scala_2.12.tgz
	mv flink-1.10.0-bin-scala_2.12.tgz flink
	mv flink /usr/local/
	vim ~/.bash_profile
	source ~/.bash_profile

查看版本号

	flink --version

开启job

	./start-cluster.sh

编写WordsCount

	./stop-cluster.sh



