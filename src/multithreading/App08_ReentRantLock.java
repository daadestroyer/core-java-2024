package multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class SharedRes {
    boolean isAvailable = false;

    public void producer(ReentrantLock lock) {
        try {
            lock.lock();
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("Lock released by : " + Thread.currentThread().getName());
        }
    }
}

public class App08_ReentRantLock {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        SharedRes sharedRes1 = new SharedRes();
        Thread t1 = new Thread(() -> {
            sharedRes1.producer(lock);
        });

        SharedRes sharedRes2 = new SharedRes();
        Thread t2 = new Thread(() -> {
            sharedRes2.producer(lock);
        });

        t1.start();
        Thread.sleep(100);
        t2.start();
    }
}