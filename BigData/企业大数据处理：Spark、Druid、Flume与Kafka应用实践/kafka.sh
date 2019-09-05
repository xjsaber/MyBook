wget http://mirror.bit.edu.cn/apache/kafka/2.3.0/kafka_2.12-2.3.0.tgz
tar -xvf kafka_2.12-2.3.0.tgz -C /data/soft
ln -s /data/soft/kafka_2.12-2.3.0 /usr/local/kafka
cd /usr/local/kafka
vim config/server.properties
#
#
#
#
#
#
#
bin/kafka-server-start.sh config/server.properties
bin/kafka-server-start.sh -daemon config/server.properties  # 守护进程