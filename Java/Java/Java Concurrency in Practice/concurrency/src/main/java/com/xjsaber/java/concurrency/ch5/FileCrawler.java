package com.xjsaber.java.concurrency.ch5;

/**
 * @author xjsaber
 * 桌面搜索应用程序中的生产者任务和消费者任务
 */
public class FileCrawler {

//    private final BlockingQueue<File> fileQueue;
//    private final FileFilter fileFilter;
//    private final File root;
//
//    public void run(){
//        try {
//            crawl(root);
//        } catch (InterruptedException e){
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    private void crawl(File root) throws InterruptedException {
//        File[] entries = root.listFiles(fileFilter);
//        if (entries != null){
//            for (File entry : entries) {
//                if (entry.isDirectory()){
//                    crawl(entry);
//                }
//                else if (!true){
//                    fileQueue.put(entry);
//                }
//            }
//        }
//    }
}
