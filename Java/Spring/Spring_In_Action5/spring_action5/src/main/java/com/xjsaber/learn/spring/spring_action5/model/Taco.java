package com.xjsaber.learn.spring.spring_action5.model;

import lombok.Data;

import java.util.List;

/**
 * @author xjsaber
 */
@Data
public class Taco {

    private String name;
    private List<String> ingredients;
}
