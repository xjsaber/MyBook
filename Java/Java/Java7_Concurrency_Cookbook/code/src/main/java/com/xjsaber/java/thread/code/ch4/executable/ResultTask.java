package com.xjsaber.java.thread.code.ch4.executable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author xjsaber
 */
public class ResultTask extends FutureTask<String> {

    private String name;
    public ResultTask(Callable<String> callable){
        super(callable);
        this.name = ((ExecutableTask)callable).getName();
    }

    @Override
    protected void done(){
        if (isCancelled()){
            System.out.printf("%s: Has been canceled\n", name);
        } else {
            System.out.printf("%s: Has been finished\n", name);
        }
    }
}
