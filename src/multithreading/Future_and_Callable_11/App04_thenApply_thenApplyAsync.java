package multithreading.Future_and_Callable_11;

import java.util.concurrent.*;

public class App04_thenApply_thenApplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // thenApply() function
        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            // Task which threads need to be execute
            System.out.println("Thread name of SupplyAsync : " + Thread.currentThread().getName());
            return "The Coders";
        },poolExecutor).thenApplyAsync((String val) -> {
            // functionality which can work on the result of previous task
            System.out.println("Thread name of ThenApply : " + Thread.currentThread().getName());
            return val + "TV";
        });
        System.out.println(asyncTask1.get());
    }
}
