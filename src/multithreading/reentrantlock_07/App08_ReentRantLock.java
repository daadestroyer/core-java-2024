package multithreading.reentrantlock_07;

import java.util.concurrent.locks.ReentrantLock;

class SharedRes {
    public void producer(ReentrantLock lock) {
        lock.lock();
        try {
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
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
        ReentrantLock lock = new ReentrantLock(true);

        SharedRes sharedRes1 = new SharedRes();
        Thread t1 = new Thread(() -> {
            sharedRes1.producer(lock);
        });

        SharedRes sharedRes2 = new SharedRes();
        Thread t2 = new Thread(() -> {
            sharedRes2.producer(lock);
        });

        t1.start();
        t2.start();
    }
}