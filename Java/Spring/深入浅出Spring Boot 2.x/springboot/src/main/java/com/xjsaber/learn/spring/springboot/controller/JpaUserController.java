package com.xjsaber.learn.spring.springboot.controller;

import com.xjsaber.learn.spring.springboot.dao.JpaUserRepository;
import com.xjsaber.learn.spring.springboot.pojo.UserJpa;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author xjsaber
 */
@Controller
@RequestMapping("/jpa")
public class JpaUserController {

    /**
     * 注入JPA接口
     */
    private JpaUserRepository jpaUserRepository = null;

    public JpaUserController(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public UserJpa getUser(Long id){
        return jpaUserRepository.findById(id).get();
    }
}
