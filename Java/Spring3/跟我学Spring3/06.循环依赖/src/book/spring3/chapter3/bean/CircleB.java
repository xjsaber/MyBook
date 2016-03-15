package book.spring3.chapter3.bean;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class CircleB {
    private CircleC circleC;
    public CircleB() {
    }
    public CircleB(CircleC circleC) {
        this.circleC = circleC;
    }
    public void setCircleC(CircleC circleC)
    {
        this.circleC = circleC;
    }
    public void b() {
        circleC.c();
    }
}