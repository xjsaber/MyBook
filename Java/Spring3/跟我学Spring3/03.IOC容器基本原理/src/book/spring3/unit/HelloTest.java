package book.spring3.unit;
import book.spring3.servcie.HelloApi;
import org.testng.annotations.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Created by xjsaber on 3/14/2016.
 */

public class HelloTest {
    @Test
    public void testHelloWorld(){

        //1.读取配置文件实例化一个IoC容器
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:helloworld.xml");
        //2.从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”
        HelloApi helloApi = context.getBean("hello", HelloApi.class);
        //3.执行业务代码
        helloApi.sayHello();
    }
}
