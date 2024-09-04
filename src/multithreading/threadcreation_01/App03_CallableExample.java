package multithreading.threadcreation_01;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class CallableWorker implements Callable {
    @Override
    public Object call() throws Exception {
        // Create random number generator
        Random random = new Random();
        int randomNumber = random.nextInt(5);
        Thread.sleep(1000);
        return randomNumber;
    }
}

public class App03_CallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // FutureTask is a concrete class that
        // implements both Runnable and Future
        FutureTask[] futureTask = new FutureTask[5];
        CallableWorker callableWorker = new CallableWorker();
        for (int i = 0; i < 5; i++) {
            // creating future task with callable and storing it in Future Task array
            futureTask[i] = new FutureTask(callableWorker);

            // creating thread with elements of FutureTask array
            Thread thread = new Thread(futureTask[i]);
            thread.start();
        }
        // get the result of each task from FutureTask
        for(int i=0 ; i<5 ; i++){
            System.out.println(futureTask[i].get());
            // for each future task we are calling get and seeking for result and will keep on waiting till it completed
            // This method blocks till the result is obtained
            // The get method can throw checked exceptions
        }
    }
}
