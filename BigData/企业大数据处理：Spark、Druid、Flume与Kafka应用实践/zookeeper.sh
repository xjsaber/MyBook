初始化
mkdir /data/zookeeper
mkdir /data/zookeeper/data
下载安装包并解压
wget http://mirror.bit.edu.cn/apache/zookeeper/zookeeper-3.5.5/apache-zookeeper-3.5.5.tar.gz
#解压到/data/soft目录下
tar -zxvf apache-zookeeper-3.5.5.tar.gz -C /data/soft
#创建软连接便于以后升级版本
ln -s /data/soft/apache-zookeeper-3.5.5 /usr/local/zookeeper
#设置环境变量
vim /etc/profile
export ZOOKEEPER_HOME=/usr/local/zookeeper
export PATH=$PATH: $ZOOKEEKER_HOME/bin
# 刷新环境变量使其生效：
source /etc/profile
#配置
cd /usr/local/zookeeper
#拷贝一份conf目录下的配置文件，重命名为zoo.cfg: 
cp ./conf/zoo_sample.cfg ./conf/zoo.cfg
:<<!
编辑配置文件设置关键参数：
tickTime=2000
initLimit=5
syncLimit=3
dataDir=/data/zookeeper/data
dataLogDir=/usr/local/zookeeper/logs
clientPort=2181
server.1=192.168.33.142:2888:3888
server.2=192.168.33.140:2888:3888
server.3=192.168.33.146:2888:3888
!
#创建myid文件
#启动Zookeeper
bin/zkServer.sh start
#验证安装是否成功
bin/zkServer.sh status