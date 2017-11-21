package udp13;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;

/**
 * @author xjsaber
 */
public class LogEventBroadcaster {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;
    private final File file;

    private LogEventBroadcaster(InetSocketAddress address, File file){
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        // 引导该NioDatagramChannel（无连接的）
        bootstrap.group(group).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new LogEventEncoder(address));
        this.file = file;
    }

    private void run() throws Exception{
        // 绑定channel
        Channel ch = bootstrap.bind(0).sync().channel();
        // 启动主线程
        long pointer = 0;
        for (;;){
            long len = file.length();
            if (len < pointer){
                // file was reset;
                // 如果有必要，将文件指针设置保没有任何的旧日志被发送
                pointer = len;
            } else if (len > pointer){
                // Content was added
                RandomAccessFile raf = new RandomAccessFile(file, "r");
                raf.seek(pointer);
                // 设置当前的文件指针，以确保没有任何的旧日志被发送
                String line;
                while ((line = raf.readLine()) != null){
                    // 对于每个日志条目都写入一个LogEvent到Channel中
                    ch.writeAndFlush(new LogEvent(null, -1, file.getAbsolutePath(), line));
                }
                // 存储其在文件中的当前位置
                pointer = raf.getFilePointer();
                raf.close();
            }
            try {
                // 休眠1秒；如果被中断，则退出循环；否则重新处理它
                Thread.sleep(1000);
            } catch (InterruptedException e){
                Thread.interrupted();
                break;
            }
        }
    }

    private void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception{
        if (args.length != 2){
            throw new IllegalArgumentException();
        }

        LogEventBroadcaster broadcaster = new LogEventBroadcaster(new InetSocketAddress("255.255.255.255", Integer.parseInt(args[0])), new File(args[1]));

        try {
            broadcaster.run();
        }
        finally {
            broadcaster.stop();
        }
    }
}
