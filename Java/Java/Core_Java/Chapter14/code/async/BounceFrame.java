package xjsaber.core_java.multithreaded.async;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by xjsaber on 2017/2/23.
 *
 */
class BounceFrame extends JFrame {
    private BallComponent component;

    /**
     * Constructs the frame
     */
    BounceFrame(){
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
    private void addButton(Container container, String title, ActionListener listener){
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }

    /**
     * add a ball
     */
    private void addBall(){
        Ball ball = new Ball();
        component.add(ball);
        Runnable runnable = new BallRunnable(ball, component);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
