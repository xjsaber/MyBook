package java8;

import java.util.concurrent.*;

/**
 * Created by xjsaber on 2017/4/10.
 *
 */
public class Test {

    public void Test(){

        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });

        doSomethingElse();

        try {
            Double result = future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException ee) {
            ee.printStackTrace();
        }
    }

    private double doSomeLongComputation(){
        return 1;
    }

    private void doSomethingElse(){

    }
}
