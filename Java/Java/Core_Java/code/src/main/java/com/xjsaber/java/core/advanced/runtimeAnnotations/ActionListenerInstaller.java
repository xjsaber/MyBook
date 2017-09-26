package com.xjsaber.java.core.advanced.runtimeAnnotations;

import java.lang.reflect.Method;

public class ActionListenerInstaller {

    /**
     * Processes all ActionListenerFor annotations in the given object.
     * @param obj
     */
    public static void processAnnotations(Object obj){
        try {
            Class<?> cl = obj.getClass();
            for (Method m : cl.getDeclaredMethods()){
                ActionListenerFor a = m.getAnnotations(ActionListenerFor.class);
            }
        }
    }
}
