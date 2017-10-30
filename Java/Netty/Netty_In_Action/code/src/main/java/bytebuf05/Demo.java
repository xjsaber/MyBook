package bytebuf05;

import io.netty.buffer.ByteBuf;

import java.util.Random;

/**
 * @author xjsaber
 */
public class Demo {

    public static void main(String[] args){
//        ByteBuf heapBuf = null;
//        assert heapBuf != null;
//        if (heapBuf.hasArray()) {
//            byte[] array = heapBuf.array();
//            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
//            int length = heapBuf.readableBytes();
//            handleArray(array, offset, length);
//        }

//        ByteBuf buffer = null;
//        for (int i = 0; i < buffer.capacity(); i++){
//            byte b = buffer.getByte(i);
//            System.out.println((char)b);
//        }

//        ByteBuf buffer = null;
//        assert buffer != null;
//        while (buffer.isReadable()) {
//            System.out.println(buffer.readByte());
//        }

        ByteBuf bytebuf = null;
        while (bytebuf.writableBytes() >= 4) {
            bytebuf.writeInt(random.nextInt());
        }
    }
}
