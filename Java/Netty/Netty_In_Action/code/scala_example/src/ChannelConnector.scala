import java.net.SocketAddress

import io.netty.channel.{Channel, ChannelFuture, ChannelFutureListener}
import io.netty.util.concurrent.{Future, Promise}
import sun.plugin2.message.transport.Transport

import scala.util.control.NonFatal

private[netty3] class ChannelConnector[In, Out] (
  newChannel: () => Channel,
  newTransport: Channel => Transport[In, Out]) extends (SocketAddress => Future[Transport[In, Out]]) {
  def apply(addr: SocketAddress) : Future[Transport[In, Out]] = {
    require(addr != null)
    // 如果 Channel 创建失败，那么异常将会被包装在Future中返回
    val ch = try newChannel() catch {
      case NonFatal(exc) => return Future.exception(exc);
    }
    // Transport is now bound to the channel; this is done prior to
    // it being connected so we don't lose any messages.
    // 使用 Channel创建一个新的Transport
    val transport = newTransport(ch)
    // 异步连接到远程主机
    val connectFuture = ch.connect(addr)
    // 创建一个新的 Promise，以便在连接尝试完成时及时收到通知
    val promise = new Promise[Transport[In, Out]]
    promise setInterruptHandler { case _cause =>
      // Propagate cancellations onto the netty future.
      connectFuture.cancel()
    }
    connectFuture.addListener(new ChannelFutureListener {
      override def operationComplete(f: ChannelFuture) {
        if (f.isSuccess) {
          promise.set
        } else if (f.isCancelled) {
          promise.setE
        }
      }
    })
  }
}
