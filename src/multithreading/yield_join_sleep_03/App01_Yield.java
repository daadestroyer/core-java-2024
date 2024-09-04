package multithreading.yield_join_sleep_03;
/*
1. Yield method causes to pause the execution of current thread and giving chance to remaining waiting thread for execution
2. If all waiting threads having low priority or if there is no waiting threads then same thread will keep on executing till its get completed
3. If several waiting threads are present with same priority then we can't expect which thread will get a change this is totally depends on
    Thread scheduler
4. The thread which called yield method when it will get chance again to resume its execution we can't expect that also is depends on Thread
    scheduler
 */

class YieldSharedResource {

    public void share(int i) {
        System.out.println("Shared Resource used by " + Thread.currentThread().getName()+" "+i);
    }
}

public class App01_Yield {
    public static void main(String[] args) {
        YieldSharedResource sharedResource = new YieldSharedResource();
        Thread th1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.share(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }, "Th1");

        Thread th2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                sharedResource.share(i);
                if (i == 3) {
                    Thread.yield();
                }
            }
        }, "Th2");

        th1.start();
        th2.start();
    }
}
