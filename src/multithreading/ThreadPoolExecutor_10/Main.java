package multithreading.ThreadPoolExecutor_10;

import java.util.concurrent.*;

class CustomThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(Thread.NORM_PRIORITY);
        thread.setDaemon(false);
        return thread;
    }
}

class CustomRejectionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task Rejected : "+r.toString());
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        try{
            Thread.sleep(5000);
        }
        catch (Exception e){
            // handling exception here
        }
        System.out.println("Task Processed by : "+Thread.currentThread().getName());
    }
}
public class Main {
    public static void main(String[] args) {
        // pool size : 2
        // maximum pool size :
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,10, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),new CustomThreadFactory(), new CustomRejectionHandler());

        threadPoolExecutor.allowCoreThreadTimeOut(true);
        // creating task
        for(int i=1 ; i<= 7 ; i++){
            int tempI = i;
            threadPoolExecutor.submit(()->{
                try{
                    Thread.sleep(5000);
                }
                catch (Exception e){
                    // handle exception here
                }
                System.out.println("Task "+ tempI +" processed by : "+Thread.currentThread().getName());
            });
        }
        // main thread will continue processing
        threadPoolExecutor.shutdown();
    }
}
