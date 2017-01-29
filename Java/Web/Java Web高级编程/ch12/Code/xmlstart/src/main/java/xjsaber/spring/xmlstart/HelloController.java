package xjsaber.spring.xmlstart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xjSaber on 2017/1/28.
 */
@Controller
public class HelloController {
    private GreetingService greetingService;

    @ResponseBody
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello, World";
    }

    @ResponseBody
    @RequestMapping(value = "/", params = {"name"})
    public String helloName(@RequestParam("name") String name){
        return this.greetingService.getGreeting(name);
    }

    public void setGreetingService(GreetingService greetingService) {
        this.greetingService = greetingService;
    }
}
