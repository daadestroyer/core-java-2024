package multithreading.Future_and_Callable_11;

import java.util.concurrent.*;

public class App03_CompletableFuture_supplyAsync {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1. Using supplyAsync function with pool executor
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "task completed...";
            } catch (Exception e) {
                // handle exception here
                return "task interrupted";
            }
        },poolExecutor);

        // 2. Using supplyAsync function without pool executor
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
                return "task completed...";
            } catch (Exception e) {
                // handle exception here
                return "task interrupted";
            }
        });

        System.out.println(completableFuture2.isDone());
        Thread.sleep(3000);
        System.out.println(completableFuture2.isDone());
        System.out.println(completableFuture2.get());
    }
}
