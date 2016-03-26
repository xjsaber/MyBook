package book.spring3.chapter3.unit;

import book.spring3.chapter3.bean.DependentBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class MoreDependencyInjectTest {
    @Test
    public void testDependOn() throws IOException{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:" +
                "book/spring3/chapter3/circleInjectByConstructor.xml");
        //一点要注册销毁回调，否则我们定义的销毁方法不执行
        context.registerShutdownHook();
        DependentBean dependentBean = context.getBean("dependentBean", DependentBean.class);
        dependentBean.write("aaa");
    }
}
