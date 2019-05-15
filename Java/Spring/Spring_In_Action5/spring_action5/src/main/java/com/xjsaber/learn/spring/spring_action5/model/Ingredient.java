package com.xjsaber.learn.spring.spring_action5.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author xjsaber
 */
@RequiredArgsConstructor
@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP,PROTEIN,VEGGIES,CHEESE,SAUCE
    }
}
