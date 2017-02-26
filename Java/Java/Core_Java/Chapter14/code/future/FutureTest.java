package xjsaber.core_java.multithreaded.future;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by xjsaber on 2017/2/26.
 */
public class FutureTest {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Enter base directory(e.g. /usr/local/jdk5.0/src):");
        String directory = in.nextLine();
        System.out.print("Enter keyword (e.g. volatile)");
        String keyword = in.nextLine();

        MatchCounter counter = new MatchCounter(new File(directory), keyword);
        FutureTask<Integer> task = new FutureTask<Integer>(counter);
        Thread t = new Thread(task);
        t.start();
        try {
            System.out.println(task.get() + " matching files.");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
