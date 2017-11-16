package encoding10;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 通过该解码器和编码器实现参数化CombineByteCharCodec
 * @author xjsaber
 */
public class CombineChannelByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombineChannelByteCharCodec(){
        // 将委托实例传递给父类
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }
}
