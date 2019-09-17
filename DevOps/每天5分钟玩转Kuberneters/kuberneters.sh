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
yum install -y VirtualBox-5.2

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

#### docker ####

# https://juejin.im/post/5d7fb46d5188253264365dcf

### delete ##

yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine

# Install Docker CE
## Set up the repository
### Install required packages.
yum install yum-utils device-mapper-persistent-data lvm2

### Add Docker repository.
yum-config-manager \
  --add-repo \
  https://download.docker.com/linux/centos/docker-ce.repo

## Install Docker CE.
yum install -y docker-ce-18.09.9-3.el7 docker-ce-cli-18.09.9-3.el7 containerd.io

## Create /etc/docker directory.
mkdir /etc/docker

# Setup daemon.
# "registry-mirrors": ["https://xxxxxxxx.mirror.aliyuncs.com"], 阿里镜像
cat > /etc/docker/daemon.json <<EOF
{
  "exec-opts": ["native.cgroupdriver=systemd"], 
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "100m"
  },
  "storage-driver": "overlay2",
  "storage-opts": [
    "overlay2.override_kernel_check=true"
  ]
}
EOF

mkdir -p /etc/systemd/system/docker.service.d

systemctl enable docker
systemctl start docker

# Restart Docker
systemctl daemon-reload
systemctl restart docker



yum install docker-ce-18.09.9-3.el7 docker-ce-cli-18.09.9-3.el7 containerd.io

### 替换ali源 ###
# step 1: 安装必要的一些系统工具
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
# Step 2: 添加软件源信息
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
# Step 3: 更新并安装Docker-CE
sudo yum makecache fast
sudo yum -y install docker-ce
# Step 4: 开启Docker服务
sudo service docker start

#### 安装Kubernetes ####

cat <<EOF > /etc/yum.repos.d/kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg
exclude=kube*
EOF

yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes -y
systemctl enable kubelet && systemctl start kubelet

cat <<EOF >  /etc/sysctl.d/k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1
EOF
sysctl --system


#### 初始化Master ####

kubeadm config print init-defaults > kubeadm-init.yaml

vim kubeadm-init.yaml

# apiVersion: kubeadm.k8s.io/v1beta2
# bootstrapTokens:
# - groups:
#   - system:bootstrappers:kubeadm:default-node-token
#   token: abcdef.0123456789abcdef
#   ttl: 24h0m0s
#   usages:
#   - signing
#   - authentication
# kind: InitConfiguration
# localAPIEndpoint:
#   advertiseAddress: 10.33.30.92
#   bindPort: 6443
# nodeRegistration:
#   criSocket: /var/run/dockershim.sock
#   name: k8s-master
#   taints:
#   - effect: NoSchedule
#     key: node-role.kubernetes.io/master
# ---
# apiServer:
#   timeoutForControlPlane: 4m0s
# apiVersion: kubeadm.k8s.io/v1beta2
# certificatesDir: /etc/kubernetes/pki
# clusterName: kubernetes
# controllerManager: {}
# dns:
#   type: CoreDNS
# etcd:
#   local:
#     dataDir: /var/lib/etcd
# imageRepository: registry.cn-hangzhou.aliyuncs.com/google_containers
# kind: ClusterConfiguration
# kubernetesVersion: v1.15.0
# networking:
#   dnsDomain: cluster.local
#   serviceSubnet: 10.96.0.0/12
# scheduler: {}

# 下载镜像
kubeadm config images pull --config kubeadm-init.yaml

# 开启端口
firewall-cmd --zone=public --add-port=6443/tcp --permanent #zookeeper 集群 传递信息的端口
firewall-cmd --zone=public --add-port=10250/tcp --permanent #zookeeper 集群 传递信息的端口
firewall-cmd --reload # 重新载入防火墙

# 禁用交换分区
vim /etc/fstab 
#/dev/mapper/cl-swap     swap                    swap    defaults        0 0

# 执行初始化
kubeadm init --config kubeadm-init.yaml

# worker机器加入的命令
kubeadm join 192.168.33.142:6443 --token abcdef.0123456789abcdef \
    --discovery-token-ca-cert-hash sha256:52a27e9d5141daf83e25bcc50811142f8ccad82c76ddc267cec802b58599e33d 

#### 配置环境 ####
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
# test kubectl get node
# 下载描述文件
wget https://docs.projectcalico.org/v3.8/manifests/calico.yaml
cat kubeadm-init.yaml | grep serviceSubnet:
# serviceSubnet: 10.96.0.0/12
kubectl apply -f calico.yaml # 初始化网络

#### 安装Dashboard ####
wget https://raw.githubusercontent.com/kubernetes/dashboard/v2.0.0-beta4/aio/deploy/recommended.yaml
kubectl apply -f recommended.yaml 
kubectl get pods --all-namespaces # kubectl get pods --all-namespaces | grep dashboard

# apiVersion: v1
# kind: ServiceAccount
# metadata:
#   name: admin-user
#   namespace: kube-system
# ---
# apiVersion: rbac.authorization.k8s.io/v1
# kind: ClusterRoleBinding
# metadata:
#   name: admin-user
# roleRef:
#   apiGroup: rbac.authorization.k8s.io
#   kind: ClusterRole
#   name: cluster-admin
# subjects:
# - kind: ServiceAccount
#   name: admin-user
#   namespace: kube-system

kubectl apply -f dashboard-adminuser.yaml

#### 生成证书 ####

grep 'client-certificate-data' ~/.kube/config | head -n 1 | awk '{print $2}' | base64 -d >> kubecfg.crt
grep 'client-key-data' ~/.kube/config | head -n 1 | awk '{print $2}' | base64 -d >> kubecfg.key
openssl pkcs12 -export -clcerts -inkey kubecfg.key -in kubecfg.crt -out kubecfg.p12 -name "kubernetes-client"

# https://192.168.33.142:6443/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login

#### 登录Dashboard ####

kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep admin-user | awk '{print $1}')

# Name:         admin-user-token-rk5pg
# Namespace:    kube-system
# Labels:       <none>
# Annotations:  kubernetes.io/service-account.name: admin-user
#               kubernetes.io/service-account.uid: 705bcb3f-ff9a-4911-a2b6-03bf1a1743a0

# Type:  kubernetes.io/service-account-token

# Data
# ====
# ca.crt:     1025 bytes
# namespace:  11 bytes
# token:      eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLXJrNXBnIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiI3MDViY2IzZi1mZjlhLTQ5MTEtYTJiNi0wM2JmMWExNzQzYTAiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZS1zeXN0ZW06YWRtaW4tdXNlciJ9.guIEQgVesxuCmmmGUYrYRN-Ytw22HL8ifEOy3-0JzUvKlqNigDggvpmAveol-L9En4DEjXD5e4uP6FjqyjO5HRBDPL6HTbfiZNTDJZEsW-BrxIVZJpYKflmuU-9XbV0e-GPisbx2EXQXIdAFo69pOONms1wuJK2J4XxBDMZmuaJHBRxAR5Mo-sDHUt5hMBpJphY1UWDtFHlJ3t3F9EyFpiYrYuwgKN78Kowz-5VZmUld55FitM_MSTkDx2pOso4sHCx65PjKc6JwiO1JaklsGxJDvARDMlEeLrwwr2HMh6BCDZ-s-Ybr2VJXGoH0jv9hgdCwelbyJm8p0lRiidv_hA

