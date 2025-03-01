package multithreading.stampedlock_09;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.StampedLock;

class SharedResource {

    public void producer(StampedLock lock) {
        // the use of stamp also supports optimistic locking
        long stamp = lock.writeLock();

        try {
            System.out.println("Write lock acquired by " + Thread.currentThread().getName());
        } catch (Exception e) {

        } finally {
            lock.unlock(stamp);
            System.out.println("Write lock released by " + Thread.currentThread().getName());
        }
    }

    public void consumer(StampedLock lock) {
        long stamp = lock.readLock();
        try {
            System.out.println("Read lock acquired by " + Thread.currentThread().getName());
        } catch (Exception e) {

        } finally {
            lock.unlockRead(stamp);
            System.out.println("Read lock released by " + Thread.currentThread().getName());
        }
    }
}

public class App01_StampedLock {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();
        StampedLock stampedLock = new StampedLock();

        // Latch to ensure consumer starts after both producers finish
        CountDownLatch latch = new CountDownLatch(2);

        // Producer Thread-1
        Thread t1 = new Thread(() -> {
            sharedResource.producer(stampedLock);
            latch.countDown();  // Decrement latch after completing work
        }, "Thread-1");

        // Producer Thread-2
        Thread t2 = new Thread(() -> {
            sharedResource.producer(stampedLock);
            latch.countDown();  // Decrement latch after completing work
        }, "Thread-2");

        // Consumer Thread-3
        Thread t3 = new Thread(() -> {
            try {
                latch.await();  // Wait for both producer threads to finish
                sharedResource.consumer(stampedLock);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Thread-3");

        // Start all threads
        t1.start();
        t2.start();
        t3.start();
    }
}
