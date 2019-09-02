# 安装必要软件
yum install -y wget
yum install -y java
yum install -y maven
yum install -y vim
# 新建文件夹
mkdir /usr/workplace
mkdir /data
mkdir /data/soft
# 开启防火墙端口
firewall-cmd --zone=public --add-port=80/tcp --permanent
firewall-cmd --zone=public --add-port=2181/tcp --permanent
firewall-cmd --zone=public --add-port=2888/tcp --permanent
firewall-cmd --zone=public --add-port=3888/tcp --permanent
# 重新载入
firewall-cmd --reload