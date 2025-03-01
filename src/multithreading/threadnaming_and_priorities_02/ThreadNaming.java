package multithreading.threadnaming_and_priorities_02;

class ApnaThread extends Thread{
    public void run() {
        System.out.println("Run implemented by " + Thread.currentThread().getName());
    }
}
public class ThreadNaming {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.setName("TheCoders TV Thread");
        myThread.start();
    }
}
