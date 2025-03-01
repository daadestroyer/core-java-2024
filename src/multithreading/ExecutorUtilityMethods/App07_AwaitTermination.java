package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App07_AwaitTermination {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try{
                Thread.sleep(5000);
            }
            catch (Exception e){

            }
            System.out.println("im still alive and doing work");
        });
        executorService.shutdown();

        // main thread will wait for 2 second to get shutdown the threadpoolexecutor, after that main thread will get shutdown
        boolean isTerminated = executorService.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("is terminated : "+isTerminated);

        System.out.println("main thread terminated");

    }
}
