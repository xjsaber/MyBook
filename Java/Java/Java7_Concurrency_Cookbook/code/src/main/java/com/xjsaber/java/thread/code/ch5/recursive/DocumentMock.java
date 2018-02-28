package com.xjsaber.java.thread.code.ch5.recursive;

import javax.tools.DocumentationTool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author xjsaber
 */
public class DocumentMock extends RecursiveTask<Integer> {

    private String[][] document;
    private int start, end;
    private String word;

    public DocumentMock(String[][] document, int start, int end, String word){
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }

    @Override
    protected Integer compute() {
        int result;
        if (end - start < 10){
            result = processLines(document, start, end, word);
        } else {
            int mid = (start + end) / 2;

        }
        return null;
    }

    private Integer processLines(String[][] document, int start, int end, String word){
        List<LineTask> tasks = new ArrayList<>();
        for (int i = start; i < end; i++){
            LineTask task = new LineTask(document[i], 0 ,document[i].length, word);
            tasks.add(task);
        }
        invokeAll(tasks);

        int result = 0;
        for (int i = 0; i < tasks.size(); i++){
            LineTask task = tasks.get(i);
            try {
                result = result + task.get();
            }
        }
        return new Integer();
    }
}
