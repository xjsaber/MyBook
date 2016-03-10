package com.line;

import java.sql.ResultSet;

/**
 * Created by xjsaber on 3/8/2016.
 */
public class CountOnLine {
    private String userIp; //用户IP地址
    private String nowdate; //用户访问数量
    private int times; //网站的访问次数
    private DB db = new DB();
    public CountOnLine(){}
    public ResultSet addUser(){
        ResultSet resultSet = null;
        String sql = "insert into tb_IPCount values(" + this.times + ',' + this.userIp + ',' + this.nowdate + ")";
        try{
            db.doSql(sql); //执行SQL语句
            resultSet = db.search("select * from tb_IPCount"); //查询表中的记录
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet; //返回结果集
    }
    public void dbClose(){
        db.closed();
    }

}
