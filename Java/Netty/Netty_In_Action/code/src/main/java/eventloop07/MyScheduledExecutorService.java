package eventloop07;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author xjsaber
 */
public class MyScheduledExecutorService {

    /**
     * 创建一个其线程池具有 10 个线程的ScheduledExecutorService
     */
    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

    /**
     * 创建一个 Runnable，以供调度稍后执行
     */
    ScheduledFuture<?> future = executor.schedule((Runnable) () -> System.out.println("60 seconds later"), 60, TimeUnit.SECONDS);

    private void doIt(){
        // 一旦调度任务执行完成，就关闭ScheduledExecutorService 以释放资源
        executor.shutdown();
    }
}
