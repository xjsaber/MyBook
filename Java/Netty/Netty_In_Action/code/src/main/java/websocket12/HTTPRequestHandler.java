package websocket12;

import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedNioFile;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * 扩展SimpleChannelInboundHandler以处理FullHttpRequest消息
 * @author xjsaber
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final String wsUri;
    private static final File INDEX;

    HttpRequestHandler(String wsUri) {
        this.wsUri = wsUri;
    }

    static {
        URL location = HttpRequestHandler.class
                .getProtectionDomain()
                .getCodeSource().getLocation();

        try {
            String path = location.toURI() + "index.html";
            path = !path.contains("file:") ? path : path.substring(5);
            INDEX = new File(path);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Unable to locate index.html", e);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request) throws Exception {
        // 如果请求了WebSocket协议升级，则增加引用计数（调用retain()方法），并将它传递给下一个ChannelInboundHandler
        if (wsUri.equalsIgnoreCase(request.uri())) {
            ctx.fireChannelRead(request.retain());
        } else {
            // 处理100 Continue请求以符合HTTP 1.1规范
            if (HttpHeaders.is100ContinueExpected(request)) {
                send100Continue(ctx);
            }
            // 读取index.html
            RandomAccessFile file = new RandomAccessFile(INDEX, "r");
            HttpResponse response = new DefaultFullHttpResponse(request.getProtocolVersion(), HttpResponseStatus.OK);
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            // 如果请求了keep-alive，则添加所需要的HTTP头信息
            boolean keepAlive = HttpHeaders.isKeepAlive(request);
            if (keepAlive){
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length());
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            }
            // 将HttpResponse写到客户端
            ctx.write(response);
            // 将index.html写到客户端
            if (ctx.pipeline().get(SslHandler.class) == null){
                ctx.write(new DefaultFileRegion(file.getChannel(), 0, file.length()));
            }
            else {
                ctx.write(new ChunkedNioFile(file.getChannel()));
            }
            // 写LastHttpContent并冲刷至客户端
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            // 如果没有请求keep-alive，则在写操作完成后关闭Channel
            if (!keepAlive){
                future.addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    private static void send100Continue(ChannelHandlerContext ctx){
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
