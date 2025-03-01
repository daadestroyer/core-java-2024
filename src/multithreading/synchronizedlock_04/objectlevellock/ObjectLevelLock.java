package multithreading.synchronizedlock_04.objectlevellock;

class SharedResource {
    public void access() {
        lockNonStaticMethod();
    }
    public void lockNonStaticMethod() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " got the lock");
            System.out.println("synchronized code executed by " + Thread.currentThread().getName());
            System.out.println("synchronized code execution ended " + Thread.currentThread().getName());
            System.out.println("---------");
        }
    }
}

public class ObjectLevelLock {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread th1 = new Thread(() -> {
            sharedResource.access();
        },"Thread - 1");

        Thread th2 = new Thread(() -> {
            sharedResource.access();
        },"Thread - 2");

        th1.start();
        th2.start();
    }
}
