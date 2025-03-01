package multithreading.Future_and_Callable_11;

import java.util.concurrent.*;

public class App01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // in the pool submitted one task and also stored the reference of Future

        Future<?> futureObj = poolExecutor.submit(() -> {
            try {
                Thread.sleep(7000); // sleeping a thread for 7 seconds
                System.out.println("this is the task, which thread will execute");
            } catch (Exception e) {

            }
        });
        System.out.println("is Done : " + futureObj.isDone()); // main thread is checking on futureObj is this task completed // NO

        try {
            futureObj.get(2, TimeUnit.SECONDS); // here main thread waiter for 2 seconds and then checking is that task completed
            // so it will throw timeout exception as task take 7 seconds to complete
        } catch (TimeoutException e) {
            System.out.println("TimeoutException happened : task is still pending");
        } catch (Exception e) {

        }

        try {
            futureObj.get(); // here main thread will wait until task gets completed
        } catch (Exception e) {

        }

        // check the status now
        System.out.println("is Done : " + futureObj.isDone());
        System.out.println("is Cancelled : " + futureObj.isCancelled());
    }
}
