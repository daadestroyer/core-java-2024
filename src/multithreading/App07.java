package multithreading;

class ShareResource {
    boolean isAvailable = false;

    public synchronized void producer() {
        try {
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
            isAvailable = true;
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        System.out.println("Lock release by " + Thread.currentThread().getName());
    }

}

public class App07 {
    public static void main(String[] args) throws InterruptedException {
        // here on a same object ( sharedResource ) both the threads are executing and getting lock one by one
        /*
            ShareResource shareResource = new ShareResource();

            Thread t1 = new Thread(() -> {
                shareResource.producer();
            });



            Thread t2 = new Thread(() -> {
                shareResource.producer();
            });

            t1.start();
            t2.start();
         */

        // but if two objects are created (shareResource 1 and shareResource 2) then both the threads acquire each object and get lock

        ShareResource shareResource1 = new ShareResource();
        ShareResource shareResource2 = new ShareResource();

        Thread t1 = new Thread(() -> {
            shareResource1.producer();
        });
        Thread t2 = new Thread(() -> {
            shareResource2.producer();
        });
        t1.start();
        Thread.sleep(1000);
        t2.start();

    }
}
