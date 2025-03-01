package multithreading.reentrant_readwritelock_08;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource{
    public int sharedResource = 0;
    public void read(ReentrantReadWriteLock lock){
        lock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" got the read lock");
            System.out.println(Thread.currentThread().getName()+" Reading "+sharedResource);
        }
        catch (Exception e){

        }
        finally {
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName()+" released the read lock");
        }
    }

    public void write(ReentrantReadWriteLock lock,int val){
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+" got the write lock");
            System.out.println(Thread.currentThread().getName()+" Writing "+val);
            this.sharedResource = val;
        }
        catch (Exception e){

        }
        finally {
            lock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName()+" released the write lock");
        }
    }
}
public class App_ReentrantReadWriteLock {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        // Reading Thread
        Thread t1 = new Thread(()->sharedResource.read(lock),"Reader Thread 1");
        Thread t2 = new Thread(()->sharedResource.read(lock),"Reader Thread 2");

        // Writer Thread
        Thread t3 = new Thread(()->sharedResource.write(lock,42),"Writer Thread 1");

        t1.start();
        t2.start();
        t3.start();
    }
}
