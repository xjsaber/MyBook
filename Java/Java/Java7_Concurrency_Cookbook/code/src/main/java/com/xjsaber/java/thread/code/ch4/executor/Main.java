package com.xjsaber.java.thread.code.ch4.executor;

/**
 * @author xjsaber
 */
public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task " + i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
