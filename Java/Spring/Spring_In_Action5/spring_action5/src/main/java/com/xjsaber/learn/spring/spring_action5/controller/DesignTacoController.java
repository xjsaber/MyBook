package com.xjsaber.learn.spring.spring_action5.controller;

import com.xjsaber.learn.spring.spring_action5.model.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author xjsaber
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("FITO", "Flour Tortilla", Ingredient.Type.WRAP)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
//        for (Ingredient.Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(), filt)
//        }
        return "design";
    }
}
