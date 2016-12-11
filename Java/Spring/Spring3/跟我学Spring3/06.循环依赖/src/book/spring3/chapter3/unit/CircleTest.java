package book.spring3.chapter3.unit;

import org.springframework.beans.factory.BeanCurrentlyInCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class CircleTest {
    @Test(expectedExceptions = BeanCurrentlyInCreationException.class)
    public void testCircleByConstructor() throws Throwable{
        try{
            new ClassPathXmlApplicationContext("classpath:book/spring3/chapter3/circleInjectByConstructor.xml");
        }catch (Exception e){
            Throwable e1 = e.getCause().getCause();
            throw e1;
        }
    }

    @Test(expectedExceptions = BeanCurrentlyInCreationException.class)
    public void testCircleBySetterAndPrototype () throws Throwable {
        try {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                    "classpath:book/spring3/chapter3/circleInjectByConstructor.xml");
            System.out.println(ctx.getBean("circleA"));
        }
        catch (Exception e) {
            Throwable e1 = e.getCause().getCause().getCause();
            throw e1;
        }
    }
}
