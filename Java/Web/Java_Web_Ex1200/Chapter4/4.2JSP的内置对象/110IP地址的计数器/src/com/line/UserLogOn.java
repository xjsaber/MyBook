package com.line;

import javax.servlet.http.HttpSession;

/**
 * Created by xjsaber on 3/6/2016.
 */
public class UserLogOn {
    private String username;
    private String backStr = "";
    private OnLine onLine = new OnLine();
    public UserLogOn(){}

    public boolean checkUser(HttpSession session){
        boolean mark = true;
        if (this.username == null || this.username.equals("")){
            this.backStr = "<li>请输入<b>用户名！</b></li><br>";
            mark = false;
        }
        if (mark){
            onLine.setUsername(this.username);
            session.setAttribute("onLineUser", onLine); //将OnLine监听类对象存入session中
            mark = onLine.getMark();
            if (!mark)
                this.backStr = "该用户已在线!";
        }
        return mark;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getBackStr(){
        return this.backStr;
    }

}
