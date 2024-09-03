package interview;

class GeneralDetails{
    String appVersion = "1.01V";
}
class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("Run method of Thread "+Thread.currentThread().getName());
    }
}
class RunnableThread extends  GeneralDetails implements Runnable{

    @Override
    public void run() {
        System.out.println("Run method of Thread "+Thread.currentThread().getName());
    }
}
public class App01_CreatingThreadFromThreadClassVSRunnable {
    public static void main(String[] args) {
        // thread creating using runnable interface
        RunnableThread runnableThread = new RunnableThread();
        Thread t2 = new Thread(runnableThread);
        System.out.println(runnableThread.appVersion);
        t2.start();
    }
}
