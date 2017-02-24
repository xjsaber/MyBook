package xjsaber.core_java.multithreaded.sync;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xjsaber on 2017/2/23.
 */
public class BounceFrame extends JFrame {
    private BallComponent component;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    /**
     * Constructs the frame
     */
    public BounceFrame(){
        setTitle("Bounce");

        component = new BallComponent();
        add(component, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel, "Close", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * add a button to a container
     * @param container
     * @param title
     * @param listener
     */
    public void addButton(Container container, String title, ActionListener listener){
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    /**
     * add a ball
     */
    public void addBall(){
        try {
            Ball ball = new Ball();
            component.add(ball);

            for (int i = 1; i <= STEPS; i++) {
                ball.move(component.getBounds());
                component.paint(component.getGraphics());
                Thread.sleep(DELAY);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
