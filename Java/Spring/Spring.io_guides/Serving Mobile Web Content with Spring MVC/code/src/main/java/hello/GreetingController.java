package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xjsaber on 2016/7/3.
 */
@Controller
public class GreetingController {

    @RequestMapping("/greeting")
    public String greeting(){
        return "greeting";
    }

}
