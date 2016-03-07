package com.line;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * Created by xjsaber on 3/6/2016.
 */
public class OnLine implements HttpSessionBindingListener {
    private String username;
    private boolean mark=true;
    private Vector vc = new Vector();
    public void setUsername(String username){
        this.username = username;
    }
    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        ServletContext sct = session.getServletContext();
        vc = (Vector)sct.getAttribute("online");
        if (vc == null){
            vc = new Vector();
            vc.add(username);
        }else{
            if (vc.contains(username)){
                mark=false;
            }else {
                vc.add(username);
            }
            sct.setAttribute("online", vc);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        ServletContext sct = session.getServletContext();
        vc = (Vector)sct.getAttribute("online");
        if (vc != null){
            vc.removeElement(this.username);
        }
    }

    public boolean getMark(){
        return this.mark;
    }
}
