package com.xjsaber.java.netty.guide.ch2.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author xjsaber
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用，绑定监听接口
     * 创建多路复用器Selector、ServerSocketChannel，对Channel和TCP参数进行配置。
     * @param port
     */
    public MultiplexerTimeServer(int port){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            // 设置为异步非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // backlog设置为1024
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 注册到Selector，监听SelectionKey.OP_ACCEPT操作位
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop(){
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop){
            try {
                // 每隔1s都被唤醒一次
                selector.select(1000);
                // selector提供了一个无参的select方法：当有处于就绪状态的Channel时，selector将返回该Channel的SelectionKey集合。
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 通过对就绪状态的Channel集合进行迭代，可以进行网络的异步读写操作。
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try {
                        handlerInput(key);
                    } catch (Exception e){
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

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复资源
        if (selector != null){
            try {
                selector.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void handlerInput(SelectionKey key) throws IOException{
        // 根据SelectionKey的操作位进行判断即可获知网络事件的类型，通过ServerSocketChannel的accept接收客户端的连接请求并创建SocketChannel实例。
        // 完成上述操作后，相当于玩成了TCP的三次握手，TCP物理链路正式建立
        if (key.isValid()){
            // 处理新接入的请求消息
            if (key.isAcceptable()){
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()){
                // Read the data
                // 创建一个ByteBuffer，由于事先无法得知客户端发送的码流大小，例如例程，开辟了1MB的缓冲区。然后调用SocketChannel的read方法读取请求码流。
                // 1. 返回值大于0:读到了字节，对字节进行编解码；
                // 2. 返回值等于0:没有读到字节，属于正常场景，忽略；
                // 3. 返回值-1:链路已经关闭，术语正常现象，忽略；
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0){
                    // 1. 对readBytes进行flip操作，将缓冲区当前的limit设置为position，position为0，用于后续对缓冲区的读取
                    // 2. 根据缓冲区可读的字节个数创建字节数组，调用ByteBuffer的get操作将缓冲区可读的字节数组复制到新创建的字节组
                    // 3.调用字符串的构造函数创建消息体并打印
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order: " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0){
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    ; // 读到0字节，忽略
                }
            }
        }
    }

    /**
     * 将输出流写入到Socket通道中
     * 1. 将字符串编码成字节数组，根据字节数组的容量创建ByteBuffer，调用ByteBuffer的put操作将字节数组复制到缓冲区，
     * 2. 然后对缓冲区进行flip操作，最后调用SocketChannel的write方法，将缓冲区中的字节数组发送出去
     * @param channel socket通道
     * @param response 输出内容
     * @throws IOException
     */
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
