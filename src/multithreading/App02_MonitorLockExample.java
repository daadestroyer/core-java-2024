package multithreading;

class RunnableThread1 implements Runnable{
    App02_MonitorLockExample app02MonitorLockExample;

    public RunnableThread1(App02_MonitorLockExample app02MonitorLockExample) {
        this.app02MonitorLockExample = app02MonitorLockExample;
    }

    @Override
    public void run() {
        app02MonitorLockExample.task1();
    }
}

public class App02_MonitorLockExample {

    public synchronized void task1() {
        try {
            System.out.println("inside task 1");
            Thread.sleep(10000);
            System.out.println("task 1 completed");
        } catch (Exception e) {
            // handle exception
        }
    }

    public void task2() {
        System.out.println("inside task 2 before synchronized");
        synchronized (this) {
            // object level lock
            System.out.println("inside task 2 synchronized");
        }
    }

    public void task3() {
        System.out.println("inside task 3");
    }

    public static void main(String[] args) {
        App02_MonitorLockExample obj = new App02_MonitorLockExample();

        RunnableThread1 runnableObj = new RunnableThread1(obj);
        Thread t1 = new Thread(runnableObj);

        Thread t2 = new Thread(()->obj.task2());
        Thread t3 = new Thread(()->obj.task3());

        t1.start();
        t2.start();
        t3.start();
    }
}
