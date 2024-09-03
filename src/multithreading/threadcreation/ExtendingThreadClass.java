package multithreading.threadcreation;

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello " + Thread.currentThread().getName());
    }
}

public class ExtendingThreadClass {
    public static void main(String[] args) {
        int noOfThreads = 5;

        // creating 5 threads
        for (int i=1 ; i<=noOfThreads ; i++){
            MyThread myThread = new MyThread();
            Thread thread = new Thread(myThread,"thread"+i);
            thread.start();
        }
    }
}
