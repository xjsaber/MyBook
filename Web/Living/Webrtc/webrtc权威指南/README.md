# WebRTC权威指南 #

## 第1章 实时通信技术介绍 ##

### 1.1 WebRTC介绍 ###

#### 1.1.1 Web浏览模式 ####

通过HTTP在浏览器与Web服务器之间传输数据。HTTP运行于TCP的上层，或者，在某些新的网络实现中，HTTP运行于WebSocket协议之上。

#### 1.1.2 浏览器中的实时通信功能 ####

WebRTC增加了一个新特点，即浏览器与浏览器之间的交互（所谓“对等连接”）。

#### 1.1.3 WebRTC系统所含的元素 ####

#### 1.1.4 WebRTC三角形 ####

信令在WebRTC中并未实现标准化，因为它只是被视作应用程序的一部分。信令

#### 1.1.5 WebRTC梯形 ####

#### 1.1.6 WebRTC和会话启动协议SIP ####

#### 1.1.7 WebRTC与Jingle ####

Web服务器具有一个内置的可扩展消息现场协议（XMPP，也称作Jabber）服务器，该内置服务器通过另一个XMPP服务器与Jingle客户端进行通信。

#### 1.1.8 WebRTC与公共交换电话网 ####

### 1.2 WebRTC中的多种媒体流 ###

### 1.3 WebRTC中的多方会话 ###

### 1.4 WebRTC标准 ###

### 1.5 WebRTC的新功能 ###

### 1.6 重要的术语说明 ###

## 第2章 如何使用WebRtc ##

### 2.1 建立WebRTC会话 ###

建立WebRTC会话时，需要以下四个主要步骤：

1. 获取本地媒体
2. 在浏览器和对等端（其他浏览器或终端）之间建立连接。
3. 将媒体和数据通道关联至该连接。
4. 交换会话描述。

#### 2.1.1 获取本地媒体 ####

getUserMedia()

#### 2.1.2 建立对等连接 ####

RTCPeerConnection API是WebRTC的核心，其作用就是在两个对等端之间建立连接。

建立此连接需要一个新的RTCPeerConnection对象。RTCPeerConnection构造函数方法的唯一输入项是一个配置对象，该对象包含ICE（Interactive Connectivity Establishment，交互式连接建立技术）“打洞”通过各种网络地址转换（Network Address Translation， NAT）设备和防火墙所使用的信息。

#### 2.1.3 交换媒体或数据 ####

建立连接后，可将任意数量的本地媒体流关联到对等连接，以通过该连接发送至远端浏览器。
### 2.2 WebRTC联网和交互示例 ###

### 2.3 WebRTC伪码示例 ###

## 第3章 本地媒体 ##

### 3.1 WebRTC中的媒体 ###

#### 3.1.1 轨道 ####

MediaStreamTrack是WebRTC中的基本媒体单元。

#### 3.1.2 流 ####
<<<<<<< .mine

MediaStream是MediaStreamTrack对象的集合。

1. 通过从现有MediaStream中复制轨道来请求对本地媒体的访问。
2. 使用对等连接来接收新的流。

### 3.2 捕获本地媒体 ###

WebRTC定义了一个新的JavaScript方法，专门用于请求对本地媒体的访问

	// 请求对音频和视频进行访问
	getUserMedia({"audio: true", "video": true}, 
				   goUserMedia, didntGetUserMedia);
	function gotUserMedis(s) {
		console.log("Should be one audio track: " + s.getAudioTracks().length);
		console.log("Should be one video track: " + s.getAudioTracks().length)
	}
	function gotUserMedia(s) {
		var myVideoElement = getElementById("myvideoelement");
		// 通过视频元素播放捕获的MediaStream
		myVideoElement.srcObject = s;
	}

### 3.3 媒体选择和控制 ###

	var t; //将用于承载轨道
	// 请求对视频进行访问
	getUserMedia({"video": true}, 
					gotUserMedia, didntGetUserMedia);
	function gotUserMedia(s) {
		t = (s.getVideoTracks())[0];
		// 获取当前功能
		console.log("Capabilites are\n" + 
			JSON.stringify(t.getCapabilities() + "\n");
		// 设置约束
		var constraints = {
			"mandatory": {"aspectRatio": 1.333333},
			"optional": [{"width}: {"min": 640}},
						 {"height": {"max": 400}}]
		}
		t.applyCons
	}

### 3.4 媒体流示例 ###



=======















































>>>>>>> .theirs
## 第5章 对等媒体 ##

## 第8章 W3C文档 ##

## 第10章 协议 ##

## 第14章 实现和应用 ##

### 14.1 浏览器 ###

#### 14.1.1 Apple Safari ####

低版本需要用oc编译

#### 14.1.2 Goole Chrome ####

#### 14.1.3 Mozilla Firefox ####

### 14.3 STUN和TURN服务器实现 ###

