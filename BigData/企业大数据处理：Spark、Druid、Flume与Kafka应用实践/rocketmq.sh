cd /usr/workplace
wget http://mirror.bit.edu.cn/apache/rocketmq/4.4.0/rocketmq-all-4.4.0-source-release.zip
unzip rocketmq-all-4.4.0-source-release.zip 
mv rocketmq-all-4.4.0 /data/soft
mvn -Prelease-all -DskipTests clean install -U
ln -s /data/soft/rocketmq-all-4.4.0 /usr/local/rocketmq
cd /usr/local/rocketmq