package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App05_Sleep_Scenario1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            System.out.println("thread going to start it work");
        });

        executorService.shutdown();

        executorService.submit(()->{
            System.out.println("thread again going to start work");
        });
    }
}
