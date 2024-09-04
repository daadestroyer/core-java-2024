package multithreading.yield_join_sleep_03;
class SleepSharedResource {
    public void share(int i) {
        System.out.println("Shared Resource used by Thread " + Thread.currentThread().getName() + " " + i);
    }
}
public class App03_Sleep {
    public static void main(String[] args) {
        SleepSharedResource sleepSharedResource = new SleepSharedResource();
        Thread th1 = new Thread(()->{
            for(int i=1 ; i<=5 ; i++){
                sleepSharedResource.share(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"Th1");

        th1.start();
    }
}
