package multithreading.ExecutorUtilityMethods;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App08_shutdownNow_Scenario_1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try{
                Thread.sleep(15000);
            }
            catch (Exception e){

            }
            System.out.println("im still alive doing work");
        });
        
        executorService.shutdown();
        System.out.println("main thread terminated");
    }
}
