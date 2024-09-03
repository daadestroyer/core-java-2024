package multithreading;

class Resource {
    public synchronized void use() {
        System.out.println(Thread.currentThread().getName() + " is using this resource");
    }
}

public class App06_DeadLock {
    public static void main(String[] args) {
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();

        // Thread 1 tries to lock resource 1 and wait to lock resource 2
        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked resource 1");
                resource1.use();
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                }
                synchronized (resource2) {
                    System.out.println(Thread.currentThread().getName() + " locked resource2");
                }
            }
        }, "Thread1");

        // Thread 2 tries to lock resource 2 and wait to lock resource 1
        Thread t2 = new Thread(() -> {


            System.out.println(Thread.currentThread().getName() + " locked resource 2");
            resource2.use();
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {

            }

            synchronized (resource1) {
                System.out.println(Thread.currentThread().getName() + " locked resource2");
            }

        }, "Thread2");

        t1.start();
        t2.start();

    }
}
