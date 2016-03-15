package book.spring3.chapter3.bean;

/**
 * Created by xjsaber on 3/15/2016.
 */
public class CircleC {
    private CircleA circleA;
    public CircleC() {
    }
    public CircleC(CircleA circleA) {
        this.circleA = circleA;
    }
    public void setCircleA(CircleA circleA)
    {
        this.circleA = circleA;
    }
    public void c() {
        circleA.a();
    }
}