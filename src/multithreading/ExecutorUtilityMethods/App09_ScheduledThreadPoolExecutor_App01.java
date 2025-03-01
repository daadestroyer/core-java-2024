package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.*;

public class App09_ScheduledThreadPoolExecutor_App01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // passing runnable and it won't return anything
        ScheduledExecutorService poolObjRunnable = Executors.newScheduledThreadPool(5);
        poolObjRunnable.schedule(() -> {
            System.out.println("hello im runnable");
        }, 5, TimeUnit.SECONDS);

        // passing callable and it will return anything
        ScheduledExecutorService poolObjCallable = Executors.newScheduledThreadPool(5);
        ScheduledFuture<String> futureObj = poolObjCallable.schedule(() -> {
            return "hello im callable";
        }, 5, TimeUnit.SECONDS);

        System.out.println(futureObj.get());

        // at first time it will run after 3 second but after first time it will run after every 5 second and it will
        // keep on printing
        ScheduledExecutorService poolObjRunnable1 = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> helloImRunnableKaBhai = poolObjRunnable1.scheduleAtFixedRate(() -> {
            System.out.println("hello im runnable ka bhai");
        }, 3, 5, TimeUnit.SECONDS);

        // that's why we need to define after how much time we need to cancel it
        try{
            Thread.sleep(10000);
            helloImRunnableKaBhai.cancel(true);
            System.out.println("10 seconds completed");
        }
        catch (Exception e){

        }
    }
}
