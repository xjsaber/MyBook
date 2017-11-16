package initizlizer11;

import com.google.protobuf.MessageLite;
import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

/**
 * 使用protobuf
 * @author xjsaber
 */
public class ProtoBufInitializer extends ChannelInitializer<Channel>{

    private final MessageLite lite;

    public ProtoBufInitializer(MessageLite lite){
        this.lite = lite;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new ProtobufDecoder(lite));
        pipeline.addLast(new ObjectHandler());
    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<ObjectHandler> {

        @Override
        protected void channelRead0(ChannelHandlerContext channelHandlerContext, ObjectHandler objectHandler) throws Exception {
            // Do something with the object
        }
    }
}
