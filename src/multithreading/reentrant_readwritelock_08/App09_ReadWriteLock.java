package multithreading.reentrant_readwritelock_08;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ShareddResource {
    boolean isAvailable = false;

    public void produce(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();
            System.out.println("write Lock acquired by " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
            System.out.println("write lock release by " + Thread.currentThread().getName());
        }
    }

    public void consume(ReadWriteLock lock) {
        try {
            lock.readLock().lock();
            System.out.println("read lock acquired by " + Thread.currentThread().getName());
            isAvailable = false;
        } catch (Exception e) {

        } finally {
            lock.readLock().unlock();
            System.out.println("read lock release by " + Thread.currentThread().getName());
        }
    }
}

public class App09_ReadWriteLock {
    public static void main(String[] args) {
        ShareddResource shareddResource = new ShareddResource();

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        Thread producerThread1 = new Thread(() -> {
            shareddResource.produce(reentrantReadWriteLock);
        }, "Thread - 1");

        ShareddResource shareddResource1 = new ShareddResource();

        Thread consumerThread1 = new Thread(() -> {
            shareddResource1.consume(reentrantReadWriteLock);
        }, "Thread - 2");

        Thread consumerThread2 = new Thread(() -> {
            shareddResource1.consume(reentrantReadWriteLock);
        }, "Thread - 3");

        producerThread1.start();
        consumerThread1.start();
        consumerThread2.start();

    }
}
