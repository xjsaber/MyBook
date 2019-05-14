package com.xjsaber.learn.spring.base;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xjsaber
 */
@RestController
public class ExampleController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
