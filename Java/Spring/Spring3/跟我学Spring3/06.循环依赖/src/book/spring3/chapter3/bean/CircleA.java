package book.spring3.chapter3.bean;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class CircleA {
    private CircleB circleB;
    public CircleA() {
    }
    public CircleA(CircleB circleB) {
        this.circleB = circleB;
    }
    public void setCircleB(CircleB circleB)
    {
        this.circleB = circleB;
    }
    public void a() {
        circleB.b();
    }
}