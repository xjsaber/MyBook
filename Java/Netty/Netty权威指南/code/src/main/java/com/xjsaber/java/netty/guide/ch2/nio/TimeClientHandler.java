package com.xjsaber.java.netty.guide.ch2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable{

    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean stop;

    /*
    初始化NIO的多路复用器和SocketChannel对象，创建SocketChannel之后，需要将其设置为异步非阻塞模式
     */
    TimeClientHandler(String host, int port){
        this.host = host == null ? "127.0.0.1" : host;
        this.port = port;
        try {
            selector = Selector.open();
            // 打开SocketChannel，绑定客户端本地地址
            socketChannel = SocketChannel.open();
            // 设置为非阻塞模式，同时设置客户端连接TCP参数
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        } catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while (!stop){
            try {
                // 多路复用器在线程run方法的无限循环体内轮询准备就绪的Key
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = ((Set) selectionKeys).iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e){
                        if (key != null){
                            key.cancel();
                            if (key.channel() != null){
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 1. 对SelectionKey进行判断，看他处于什么状态
     * 2. 处于连接状态，说明服务端已经返回ACK应答消息
     * 3. 对连接结果进行判断，调用SocketChannel的finishConnect()方法
     * 3.1 返回值true，说明客户端连接成功
     * 3.2 返回值false，或者直接抛出IOException，说明连接失败
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            // 判断是否连接成功
            SocketChannel sc = (SocketChannel)key.channel();
            // 接受connect事件进行处理
            if (key.isConnectable()){
                // 判断连接结果，如果连接成功，注册读事件到多路复用器
                if (sc.finishConnect()){
                    // 注册读事件到多路复用器
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                } else {
                    // 连接失败，进程退出
                    System.exit(1);
                }
            }
            if (key.isReadable()){
                // 预先分配1MB的接收缓冲区用于读取应答消息
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 异步读客户端请求消息到缓冲区
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("Now is : " + body);
                    this.stop = true;
                } else if (readBytes < 0){
                    key.cancel();
                    sc.close();
                } else {
                    ; // 读到0字节，忽略
                }
            }
        }
    }

    /**
     * 1. 对SocketChannel的connect()操作进行判断
     * 1.1 连接成功，将SocketChannel注册到多路复用器Selector上，注册SelectionKey.OP_READ
     * 1.2 没有连接成功，则说明服务端没有返回TCP握手应答消息，但不代表连接失败
     * 1.2.1 将SocketChannel注册到多路复用器Selector上
     * 1.2.2 注册SelectionKey.OP_CONNECT
     * 1.2.3 服务器返回TCP syn-ack消息后，Selector轮询到SocketChannel处于连接就绪状态
     * @throws IOException
     */
    private void doConnect() throws IOException {
        // 异步连接服务端
        // 判断是否连接成功，如果连接成功，则直接注册读状态到多路复用中，如果当前没有连接状态（异步连接，返回false，说明客户端已经发送sync包，服务端没有返回ack包，物理链路还没有建立
        if (socketChannel.connect(new InetSocketAddress(host, port))){
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            // 想reactor线程的多路复用器注册OP_CONNECT状态位，监听服务器的TCPACK应答
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    /**
     * 请求消息体，然后对其编码，写入到发送缓冲区，最后调用SocketChannel的write方式进行发送
     * 由于发送是异步的，所以存在“半包写”的问题
     * @param sc
     * @throws IOException
     */
    private void doWrite(SocketChannel sc) throws IOException {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        sc.write(writeBuffer);
        if (!writeBuffer.hasRemaining()){
            System.out.println("Send order 2 server succeed.");
        }
    }
}
