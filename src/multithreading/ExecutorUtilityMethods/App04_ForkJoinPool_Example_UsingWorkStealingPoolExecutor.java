package multithreading.ExecutorUtilityMethods;

import java.util.concurrent.*;

class ComputeSumTask extends RecursiveTask<Integer> {
    // Whenever your subtask need to return anything RecursiveTask
    int start;
    int end;
    public ComputeSumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if (end - start <= 4) { // if there are 4 values left only then i don't need to divide further
            int totalSum = 0;
            for (int i = start; i <= end; i++) {
                totalSum += i;
            }
            return totalSum;
        } else {
            // split the task
            int mid = (start + end) / 2;
            ComputeSumTask leftTask = new ComputeSumTask(start, mid);
            ComputeSumTask rightTask = new ComputeSumTask(mid + 1, end);
            // for the subtask for parallel execution
            leftTask.fork();
            rightTask.fork();
            // combine the results of subtask
            int leftRes = leftTask.join();
            int rightRes = rightTask.join();
            // combine the results
            return leftRes + rightRes;
        }
    }

}

public class App04_ForkJoinPool_Example_UsingWorkStealingPoolExecutor {
    public static void main(String[] args) {

        // 1. call Executors.workStealingPool()
        ExecutorService executorService = Executors.newWorkStealingPool(); // by default it will create thread based on current available processor
        // but there is another overloaded method newWorkStealingPool() and here
        // we can provide no of thread we want

        // casting executorService to ForkJoinPool as ExecutorService submit method expect Callable or Runnable
        // and we need to pass object into submit method
        // submit method of forkJoinPool can takes object of ComputeSumTask
        ForkJoinTask<Integer> forkJoinTask = ((ForkJoinPool) executorService).submit(new ComputeSumTask(0, 100));
        try {
            System.out.println("Sum : " + forkJoinTask.get());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            executorService.shutdown(); // Shutdown the executor
        }

        System.out.println("---------------");

        // 2. either call directly ForkJoinPool
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        ForkJoinTask<Integer> futureObj = forkJoinPool.submit(new ComputeSumTask(0, 10));
        try {
            System.out.println(futureObj.get());
        } catch (Exception e) {
            System.out.println(e);
        }


    }
}


// 10 9 8 7 6 5 4 3 2 1