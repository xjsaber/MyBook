package xjsaber.core_java.multithreaded.async;

import java.awt.*;

/**
 * Created by xjsaber on 2017/2/23.
 *
 */
public class BallRunnable implements Runnable {
    private static final int STEPS = 1000;
    private static final int DELAY = 3;

    private Ball ball = null;
    private Component component = null;

    BallRunnable(Ball ball, Component component) {
        this.ball = ball;
        this.component = component;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < STEPS; i++) {
                ball.move(component.getBounds());
                component.paint(component.getGraphics());
                Thread.sleep(DELAY);
            }
        }catch (InterruptedException ignored){

        }
    }
}
