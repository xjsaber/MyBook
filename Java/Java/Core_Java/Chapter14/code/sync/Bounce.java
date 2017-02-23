package xjsaber.core_java.multithreaded.sync;

//import java.awt.*

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by xjSaber on 2017/2/23.
 * 显示ball
 */
public class Bounce {

    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        EventQueue.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new BounceFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
