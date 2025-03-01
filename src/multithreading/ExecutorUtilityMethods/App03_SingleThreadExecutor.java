package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App03_SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService poolExecutor = Executors.newSingleThreadExecutor();
        poolExecutor.submit(()->"This is async task");
    }
}
