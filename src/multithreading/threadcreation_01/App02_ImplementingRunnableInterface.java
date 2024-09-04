package multithreading.threadcreation_01;

class MeraThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Run hora hai "+Thread.currentThread().getName());
    }
}
public class App02_ImplementingRunnableInterface {
    public static void main(String[] args) {
        int noOfThreads = 5;
        for(int i=1 ; i<= noOfThreads ; i++){
            MeraThread meraThread = new MeraThread();
            Thread thread = new Thread(meraThread,"Thread "+i);
            thread.start();
        }
    }
}
