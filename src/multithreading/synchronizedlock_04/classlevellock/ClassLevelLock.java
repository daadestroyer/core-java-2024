package multithreading.synchronizedlock_04.classlevellock;


class SharedResouce {
    public void access() {
        lockNonStaticMethod();
    }

    public static synchronized void lockNonStaticMethod() {
        synchronized (SharedResouce.class) {
            System.out.println(Thread.currentThread().getName() + " got the lock");
            System.out.println("synchronized code executed by " + Thread.currentThread().getName());
            System.out.println("synchronized code execution ended " + Thread.currentThread().getName());
            System.out.println("---------");
        }
    }


}

public class ClassLevelLock {
    public static void main(String[] args) {
        SharedResouce sharedResource = new SharedResouce();
        Thread th1 = new Thread(() -> {
            sharedResource.access();
        }, "Thread - 1");

        Thread th2 = new Thread(() -> {
            sharedResource.access();
        }, "Thread - 2");

        th1.start();
        th2.start();
    }
}
