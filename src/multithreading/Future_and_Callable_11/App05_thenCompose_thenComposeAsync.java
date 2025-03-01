package multithreading.Future_and_Callable_11;

import java.util.concurrent.*;

public class App05_thenCompose_thenComposeAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread name which runs 'supplyAsync' " + Thread.currentThread().getName());
            return "The Coders ";
        },poolExecutor).thenCompose((String val) -> {
            return CompletableFuture.supplyAsync(() -> {
                System.out.println("Thread name which runs 'thenCompose' " + Thread.currentThread().getName());
                return val + "TV";
            });
        });

        System.out.println(completableFuture.get());
    }
}
