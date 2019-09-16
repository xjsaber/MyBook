##### kubectl #####
# Download the latest release with the command:
curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl
# Make the kubectl binary executable.
chmod +x ./kubectl
# Move the binary in to your PATH.
sudo mv ./kubectl /usr/local/bin/kubectl
# Test to ensure the version you installed is up-to-date:
kubectl version

##### minikube #####

# Install Minikube via direct download
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
  && chmod +x minikube

sudo install minikube /usr/local/bin

##### Installing Kubernetes with Minikube #####

##### VirtualBox-6.x #####
yum install SDL -y
# libXmu.so.6()(64bit) 被 VirtualBox-6.0-6.0.12_133076_el7-1.x86_64 需要
# libXt.so.6()(64bit) 被 VirtualBox-6.0-6.0.12_133076_el7-1.x86_64 需要
# libopus.so.0()(64bit) 被 VirtualBox-6.0-6.0.12_133076_el7-1.x86_64 需要
# libvpx.so.1()(64bit) 被 VirtualBox-6.0-6.0.12_133076_el7-1.x86_64 需要
yum install libXmu -y
yum install opus -y
yum install libXt -y
yum install libvpx -y
yum install qt qt-x11 gcc gcc-c++ kernel-devel perl -y
wget https://download.virtualbox.org/virtualbox/6.0.12/VirtualBox-6.0-6.0.12_133076_el7-1.x86_64.rpm
rpm -ivh VirtualBox-6.0-6.0.12_133076_el7-1.x86_64.rpm

##### VirtualBox-5.2 #####
# 安装源
yum install epel-release
# 添加VirtualBox安装源
cd /etc/yum.repos.d/
wget http://download.virtualbox.org/virtualbox/rpm/rhel/virtualbox.repo 
# 安装相关依赖包
yum update
yum install binutils qt gcc make patch libgomp glibc-headers glibc-devel kernel-headers kernel-devel dkms
# https://www.cnblogs.com/hongdada/p/9578849.html

### 内核版本需要统一
# grub2-set-default 'CentOS Linux (3.10.0-957.27.2.el7.x86_64) 7 (Core)' 修改启动内核
# grub2-editenv list
### This computer doesn't have VT-X/AMD-v enabled. Enabling it in the BIOS is mandatory
### Suggestion: In some environments, this message is incorrect. Try 'minikube start --no-vtx-check'
### VT-x is not available (VERR_VMX_NO_VMX)



##### minikube #####
# 启动
minikube start
# 
kubectl run hello-minikube --image=k8s.gcr.io/echoserver:1.10 --port=8080
# To access the hello-minikube Deployment, expose it as a Service
kubectl expose deployment hello-minikube --type=NodePort
kubectl get pod
minikube service hello-minikube --url

##### Web UI (Dashboard) #####
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta4/aio/deploy/recommended.yaml
# kubectl proxy --port=8009 Starting to serve on 127.0.0.1:8009
kubectl proxy --address=0.0.0.0  # --port=8009 如果想通过其它主机访问就需要指定监听的地址，远端访问会出现未认证
kubectl proxy --address='0.0.0.0'  --accept-hosts='^*$' --port=8009	 # 设置API server接收所有主机的请求
# apiserver
kubectl get apiservices
# 防火墙
firewall-cmd --zone=public --add-port=8001/tcp --permanent #kubernetes-dashboard webui
firewall-cmd --reload # 重新载入防火墙
# 测试
curl http://localhost:8001/api/

# 重启
reboot