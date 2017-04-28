import com.sun.deploy.nativesandbox.comm.{Request, Response}

import scala.language.higherKinds

/**
  * Created by xjsaber on 2017/4/28.
  *
  */
class Application {

  def application[IN[_], OUT[_]](f: Request[IN] => Response[OUT]) = new Application[IN, OUT] {
    def apply(implicit req: Request[IN]): Response[OUT] = f(req)
  }
}
