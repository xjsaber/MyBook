package readinglist;

import org.springframework.context.annotation.Conditional;

/**
 * Created by xjsaber on 2017/4/28.
 *
 */
public class MyService {

    @Conditional(JdbcTemplateCondition.class)
    public void HelloWorld(){

        System.out.println(" Hello World");
    }
}
