package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App02_CachedThreadPoolExecutor {
    public static void main(String[] args) {
        // cached thread pool executor
        ExecutorService poolExecutor = Executors.newCachedThreadPool();
        poolExecutor.submit(()->"this is async task");
    }
}
