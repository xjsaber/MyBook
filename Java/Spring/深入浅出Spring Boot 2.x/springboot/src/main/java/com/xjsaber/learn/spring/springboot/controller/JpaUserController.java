package com.xjsaber.learn.spring.springboot.controller;

import com.xjsaber.learn.spring.springboot.dao.JpaUserRepository;
import com.xjsaber.learn.spring.springboot.pojo.JpaUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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
    public Optional<JpaUser> getUser(Long id){
        return jpaUserRepository.findById(id);
    }

    @RequestMapping("/findByUserNameLike")
    @ResponseBody
    public List<JpaUser> findByUserNameLike(String userName){
        return jpaUserRepository.findByUserNameLike(userName);
    }

    @RequestMapping("/findByUserNameLikeOrNoteLike")
    @ResponseBody
    public List<JpaUser> findByUserNameLikeOrNoteLike(String userName, String note){
        String userNameLike = "%" + userName + "%";
        String noteLike = "%" + note + "%";
        return jpaUserRepository.findByUserNameLikeOrNoteLike(userNameLike, noteLike);
    }
}
