# ch4 Internet地址 #

## InetAddress类 ##
java.net.InetAddress类是Java对IP地址（包括IPv4和IPv6）的高层表示。Socket、ServerSocket、URL、DatagramSocket、DatagramPacket等。

### 创建新的InetAddress对象 ###
第一个InetAddress.getByAddress()工厂方法用一个IP地址（而没有主机名）创建一个InetAddress对象。第二个InetAddress.getAddress()方法使用一个IP地址和一个主机名创建InetAddress对象。

### 缓存 ###
