# Spark大数据实战班 #

## 大数据入门概述 ##

大数据时代——浪潮之巅

什么是大数据：不仅是大量数据

* 数据量大
* 数据多样性
	* 结构化和非结构化

---

* 处理速度快
* 真实性
* 价值密度低 
	 
思想之源

* 大量的网页怎么存储（运用冗余防止数据丢失）
	* 分布式文件系统GFS 
* Page-Rank的计算问题（单台机器不够算）
	* 分布式计算框架Map-Reduce
* 如何快速查到数据
	* NoSQL数据库系统Bigtable
	
#### Haoop的组成 ####

* 大数据计算，MapReduce
* 资源管理与调度，Yarn
* 大数据存储，HDFS
* 辅助工具，Common

#### 三大发行版本 ####

* Apache
* Cloudera
* Hortonworks

#### 生态圈 ####

分析报告工具：Zeooeline

### HDFS的原理与架构 ###

HDFS集群包括，主节点（NameNode）和数据节点（DataNode）以及从节点（Secondary Namenode）。

* 主节点（NameNode）：负责管理整个文件系统的元数据，以及每一个路径（文件）所对应的数据块信息。
* 数据节点（DataNode）：负责管理用户的文件数据块，每一个数据块都可以在多个datanode上存储多个副本。
* 从节点（Secondary NameNode）：用来监控HDFS状态的辅助后台程序，每隔一段时间获取HDFS元数据的快照。

HDFS的HA
