package multithreading.stampedlock_09;

import java.util.concurrent.locks.StampedLock;

class SharedResourceOptimistic {

    int a = 10;  // Initial value of 'a'
    int temp = a;

    // Optimistic Read Operation
    public void optimisticRead(StampedLock lock) {
        long stamp = lock.tryOptimisticRead();  // Try optimistic read without locking

        try {
            System.out.println("Optimistic Read taken by " + Thread.currentThread().getName());


            // Simulate some work with the value
            System.out.println("Current value of 'a' before any changes: " + a);
            Thread.sleep(2000);  // Simulating delay in processing

            // After delay, check if the value was modified
            if (lock.validate(stamp)) {
                a = 200;
                // If the value wasn't changed by a writer thread, continue working with the updated value
                System.out.println("Optimistic read validated, value of 'a' is : " + a);
            } else {
                // If the value was changed by a writer thread, update with the latest value
                System.out.println("Optimistic read invalidated, new value of 'a' is : " + a);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Writer Method with Write Lock
    // Write Operation
    public void writer(StampedLock lock) {
        long stamp = lock.writeLock();  // Acquire write lock to modify the value
        System.out.println("Write lock acquired by: " + Thread.currentThread().getName());

        try {
            System.out.println("Performing write operation, changing value of 'a'");
            a = 9;  // Writer modifies the value
            System.out.println("Value of 'a' after writer changes to " + a+" from "+ temp);
        } finally {
            lock.unlockWrite(stamp);  // Release the write lock
            System.out.println("Write lock released by: " + Thread.currentThread().getName());
        }
    }
}

public class App02_OptimisticLock {
    public static void main(String[] args) throws InterruptedException {
        SharedResourceOptimistic sharedResourceOptimistic = new SharedResourceOptimistic();
        StampedLock stampedLock = new StampedLock();

        // Reader thread simulating optimistic read
        Thread reader = new Thread(() -> {
            sharedResourceOptimistic.optimisticRead(stampedLock);
        }, "Reader Thread");

        // Writer thread simulating write operation
        Thread writer = new Thread(() -> {
            sharedResourceOptimistic.writer(stampedLock);
        }, "Writer Thread");

        writer.start();  // Start reader thread
        // Introduce a small delay to ensure reader starts first before writer
        Thread.sleep(1000);


        reader.start();  // Start writer thread

        // main thread Wait for both threads to complete
        // Without join(), the main thread might finish before the reader and writer threads, and the program might terminate before those threads have completed their tasks.
        // By using join(), you ensure that the main thread will only finish after the reader and writer threads have completed their work.
        reader.join();
        writer.join();
    }
}