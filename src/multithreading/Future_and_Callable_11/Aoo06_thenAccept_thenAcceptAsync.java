package multithreading.Future_and_Callable_11;

import java.util.concurrent.*;

public class Aoo06_thenAccept_thenAcceptAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread name which runs 'supplyAsync' :" + Thread.currentThread().getName());
            return "The Coders ";
        }).thenAccept((String val) -> System.out.println("All stages completed"));

        System.out.println(completableFuture.get());

    }
}
