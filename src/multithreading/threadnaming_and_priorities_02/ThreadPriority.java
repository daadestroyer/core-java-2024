package multithreading.threadnaming_and_priorities_02;

class MyThread extends Thread {
    public void run() {
        System.out.println("Run implemented by " + Thread.currentThread().getName() + " having priority " + Thread.currentThread().getPriority());
    }
}

public class ThreadPriority {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("TheCoders TV Thread");

        System.out.println("Default priority of " + myThread.getName() + " is " + myThread.getPriority());
        myThread.setPriority(10); // setting highest priority
        myThread.start();
    }
}
