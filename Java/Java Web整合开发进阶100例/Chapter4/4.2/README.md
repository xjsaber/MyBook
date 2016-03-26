#4.2拦截器原理实现
##技术要点
* 拦截器类和被拦截类内容
* 运用反射机制调用类和类方法。
* 设置拦截器处理类，配置拦截器在何时执行以及拦截器类和被拦截类执行的先后顺序。
* 设置代理对象类实现拦截器拦截功能。
* 测试程序运行结果显示拦截功能正常执行情况

1. ExecuteFunction是一个正常执行业务逻辑类的Java类，
2. 为了让拦截器类和被拦截的功能执行类发生关联关系，使用Java中的反射机制。在InterceptorHandler类中，通过扩展java.lang.reflect.InvocationHandler接口，覆盖重写invoke方法，该方法用method.invoke来调用再通过setObject方法传入的功能执行类对象的方法。
3. 创建ProxyObject对象是想通过使用设计模式中的代理模式


	