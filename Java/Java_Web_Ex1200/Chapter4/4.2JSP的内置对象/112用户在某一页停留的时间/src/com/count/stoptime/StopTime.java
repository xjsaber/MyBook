package com.count.stoptime;
import java.util.*;
/**
 * Created by xjsaber on 3/9/2016.
 */
public class StopTime {
    private int h = 0;
    private int m = 0;
    private int s = 0;
    public StopTime(){}
    public void CountTime(Date startTime){
        Date endTime = new Date();  //获得当前时间
        long howmuch = endTime.getTime() - startTime.getTime(); //获得当前时间与登录时间相差的毫秒数
        h = (int)(howmuch/1000/60/60);  //计算小时
        howmuch = howmuch - h*60*60*1000;
        m = (int)(howmuch/1000/60); //计算分钟
        howmuch = howmuch - m*60*1000;
        s = (int)(howmuch/1000);    //计算停留的秒数
    }
    public int getH(){
        return h;
    }
    public int getM(){
        return m;
    }
    public int getS(){
        return s;
    }
}
