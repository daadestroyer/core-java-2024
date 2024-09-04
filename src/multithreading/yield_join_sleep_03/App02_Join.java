package multithreading.yield_join_sleep_03;

class JoinSharedResource {
    public void share(int i) {
        System.out.println("Shared Resource used by " + Thread.currentThread().getName() + " " + i);
    }
}

public class App02_Join {
    public static void main(String[] args) {
        JoinSharedResource joinSharedResource = new JoinSharedResource();
        Thread th1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                joinSharedResource.share(i);

            }
        }, "Th1");

        Thread th2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                joinSharedResource.share(i);
                if(i==3){
                    try {
                        th1.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "Th2");


        th1.start();
        th2.start();


    }
}
