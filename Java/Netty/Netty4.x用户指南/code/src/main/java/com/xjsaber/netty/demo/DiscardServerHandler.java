package com.xjsaber.netty.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理服务器 channel
 * 1. DiscardServerHandler 继承自 ChannelInboundHandlerAdapter，这个类实现了 ChannelInboundHandler接口，ChannelInboundHandler 提供了许多事件处理的接口方法，然后你可以覆盖这些方法。现在仅仅只需要继承 ChannelInboundHandlerAdapter 类而不是你自己去实现接口方法。
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 2.这里我们覆盖了 chanelRead() 事件处理方法。
     * 每当从客户端收到新的数据时，这个方法会在收到消息时被调用，这个例子中，收到的消息的类型是 ByteBuf
     * @param ctx 上下文
     * @param msg ByteBuf msg
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 3.为了实现 DISCARD 协议，处理器不得不忽略所有接受到的消息。ByteBuf 是一个引用计数对象，这个对象必须显示地调用 release() 方法来释放。请记住处理器的职责是释放所有传递到处理器的引用计数对象
        // 丢弃
        // Writing a Discard Server 写个抛弃服务器
//        ((ByteBuf) msg).release();

        // 如果想查看收到的数据的话，则进行以下操作
        // Looking into the Received Data 查看收到的数据
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            // 1.这个低效的循环事实上可以简化为:System.out.println(in.toString(io.netty.util.CharsetUtil.US_ASCII))
//            while (in.isReadable()){
//                System.out.println((char)in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            // 2. 或者，你可以在这里调用 in.release()。
//            ReferenceCountUtil.release(msg); //2
//        }

        // Looking into the Received Data 查看收到的数据
        // ChannelHandlerContext 对象提供了许多操作，使你能够触发各种各样的 I/O 事件和操作。这里我们调用了 write(Object) 方法来逐字地把接受到的消息写入。请注意不同于 DISCARD 的例子我们并没有释放接受到的消息，这是因为当写入的时候 Netty 已经帮我们释放了。
        // http://netty.io/4.0/api/io/netty/channel/ChannelHandlerContext.html
        ctx.write(msg); // (1)
        // ctx.write(Object) 方法不会使消息写入到通道上，他被缓冲在了内部，你需要调用 ctx.flush() 方法来把缓冲区中数据强行输出。或者你可以用更简洁的 cxt.writeAndFlush(msg) 以达到同样的目的。
        ctx.flush(); // (2)
    }

    /**
     * exceptionCaught() 事件处理方法是当出现 Throwable 对象才会被调用，即当 Netty 由于 IO 错误或者处理器在处理事件时抛出的异常时。在大部分情况下，捕获的异常应该被记录下来并且把关联的 channel 给关闭掉。然而这个方法的处理方式会在遇到不同异常的情况下有不同的实现，比如你可能想在关闭连接之前发送一个错误码的响应消息
     * @param ctx 上下文
     * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
