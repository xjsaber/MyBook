package base01;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

/**
 * @author xjsaber
 */
public class Demo {

    public class ConnectHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            // 新连接建立时，channelActive.
            System.out.println("Client " + ctx.channel().remoteAddress() + " connected");
        }

        public void connect(){
            Channel channel = null;
            // Dose not block
            // 异步地连接到远程节点
            ChannelFuture future = channel.connect(new InetSocketAddress("192.168.1.1", 25));
        }
    }

		public void start() throws IOException {
		        int portNumber = 8000;
		        ServerSocket serverSocket = new ServerSocket(portNumber);
		        Socket clientSocket = serverSocket.accept();
		        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		        String request, response;
		        while ((request = in.readLine()) != null){
		            if ("Done".equals(request)){
		                break;
		            }
		//            response =
		            out.println();
		        }
    }
}
