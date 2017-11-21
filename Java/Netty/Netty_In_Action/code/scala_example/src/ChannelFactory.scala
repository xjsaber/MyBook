import java.util.concurrent.Executor

import io.netty.channel.ChannelFactory

object Netty3Transporter {
  val channelFactory: ChannelFactory = new NioClientSocketChannelFactory(
    Executor, 1 /* boss thread */, WorkerPool, DefaultTimer
  ) {
    // no-op; unreleasable
    override def releaseExternalResources() = ()
  }
  val defaultChannelOptions: Map[String, Object] = Map(
    "tcpNoDelay" -> java.lang.Boolean.TRUE,
    "reuseAddress" -> java.lang.Boolean.TRUE
  )
}