package multithreading.Future_and_Callable_11;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class App02 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // Use case 1 : using first type of submit()
        Future<?> futureObj1 = poolExecutor.submit(() -> System.out.println("Task with runnable"));

        Object o = futureObj1.get();
        System.out.println(o == null);

        // Use case 2 : using second type of submit()
        ArrayList<Integer> output = new ArrayList<>();
        Future<ArrayList<Integer>> futureObj2 = poolExecutor.submit(() -> {
            output.add(100);
            System.out.println("Task2 with Runnable and return object");
        }, output);

        try {
            // 1st way
            ArrayList<Integer> outputFutureObj2 = futureObj2.get(); // main thread will wait to get this task to completed
            System.out.println(outputFutureObj2.get(0));

            // 2nd way
            System.out.println(output.get(0));

        } catch (Exception e) {

        }

        // Use case 3 : using third type of submit()
        Future<ArrayList<Integer>> taskWithCallable = poolExecutor.submit(() -> {
            System.out.println("Task with callable");
            ArrayList<Integer> listObj = new ArrayList<>();
            listObj.add(200);
            return listObj;
        });

        try{
            ArrayList<Integer> result = taskWithCallable.get();
            System.out.println(result.get(0));
        }
        catch (Exception e){

        }

    }
}
