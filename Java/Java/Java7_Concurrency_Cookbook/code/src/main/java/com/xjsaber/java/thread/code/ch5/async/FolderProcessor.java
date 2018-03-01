package com.xjsaber.java.thread.code.ch5.async;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 *
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

    private static final long serialVersionUID = 1L;
    private String path;
    private String extension;

    public FolderProcessor(String path, String extension){
        this.path = path;
        this.extension = extension;
    }

    @Override
    protected List<String> compute() {
        List<String> list = new ArrayList<>();
        List<FolderProcessor> tasks = new ArrayList<>();
        File file = new File(path);
        File[] content = file.listFiles();

        if (content != null){
            for (int i = 0; i < content.length; i++){
                if (content[i].isDirectory()){
                    FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
                    task.fork();
                    tasks.add(task);
                } else {
                }
            }
        }

        return null;
    }

    /**
     * 遍历任务列表中存储的每一个任务，调用join方法等待任务结束执行结束，并且返回任务的结果。
     * 然后调用addAll()方法将任务的结果增加到字符列表中。
     * @param list 字符串path
     * @param tasks 任务集合
     */
    private void addResultsFromTasks(List<String> list, List<FolderProcessor> tasks){
        for (FolderProcessor item : tasks){
            list.addAll(item.join());
        }
    }

    /**
     * 检查作为参数而传递进来的文件名，如果是正在搜索的文件扩展名为结尾，那么方法返回为true，否则则返回false
     * @param name path name
     * @return
     */
    private boolean checkFile(String name){
        return name.endsWith(extension);
    }
}
