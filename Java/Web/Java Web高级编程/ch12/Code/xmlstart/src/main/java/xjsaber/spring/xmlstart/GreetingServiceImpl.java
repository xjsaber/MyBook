package xjsaber.spring.xmlstart;

/**
 * Created by xjSaber on 2017/1/28.
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
