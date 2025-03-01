package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App01_FixedThreadPoolExecutor {
    public static void main(String[] args) {
        // fixed thread pool executor
        ExecutorService poolExecutor = Executors.newFixedThreadPool(5);// will create max 5 thread only and its fixed
        poolExecutor.submit(() -> "this is async task");
    }
}
