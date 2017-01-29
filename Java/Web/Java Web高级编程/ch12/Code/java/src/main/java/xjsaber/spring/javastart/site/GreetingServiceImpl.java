package xjsaber.spring.javastart.site;

import org.springframework.stereotype.Service;

/**
 * Created by xjSaber on 2017/1/28.
 */
@Service
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting(String name) {
        return "Hello, " + name + "!";
    }
}
