package xjsaber.core_java.multithreaded.sync;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by xjsaber on 2017/2/23.
 * the component that draws the balls
 */
public class BallComponent extends JPanel {
    private static final int DEFAULT_WIDTH = 450;
    private static final int DEFAULT_HEIGHT = 350;

    private java.util.List<Ball> balls = new ArrayList<>();

    public void add(Ball b){
        balls.add(b);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //erase background
        Graphics2D g2 = (Graphics2D)g;
        for (Ball b: balls) {
            g2.fill(b.getShape());
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
