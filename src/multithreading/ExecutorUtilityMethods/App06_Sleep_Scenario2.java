package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App06_Sleep_Scenario2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try{
                Thread.sleep(5000);
            }
            catch (Exception e){

            }
            System.out.println("im still alive and doing task");
        });
        executorService.shutdown();
        System.out.println("Main thread shutdown");
    }

}
