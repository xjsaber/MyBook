# 安装必要软件
yum install -y wget
yum install -y java
yum install -y maven
yum install -y vim
yum install -y zip
# 新建文件夹
mkdir /usr/workplace # 用户工作中心
mkdir /data # 
mkdir /data/soft # 软件
# 开启防火墙端口
firewall-cmd --zone=public --add-port=80/tcp --permanent
firewall-cmd --zone=public --add-port=2181/tcp --permanent #zookeeper 端口
firewall-cmd --zone=public --add-port=2888/tcp --permanent #zookeeper 集群 传递信息的端口
firewall-cmd --zone=public --add-port=3888/tcp --permanent #zookeeper 集群 传递信息的端口
firewall-cmd --zone=public --add-port=9092/tcp --permanent #kafka
firewall-cmd --zone=public --add-port=8001/tcp --permanent #kubernetes-dashboard webui
firewall-cmd --reload # 重新载入防火墙