package multithreading;

class ProduceItem implements Runnable {
    App03_SharedResource app03SharedResource;

    public ProduceItem(App03_SharedResource app03SharedResource) {
        this.app03SharedResource = app03SharedResource;
    }

    @Override
    public void run() {
        System.out.println("Producer Thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000l);
        } catch (Exception e) {
            // handle exception
        }
        app03SharedResource.produceItem();
    }
}

class ConsumeItem implements Runnable {

    App03_SharedResource app03SharedResource;

    public ConsumeItem(App03_SharedResource app03SharedResource) {
        this.app03SharedResource = app03SharedResource;
    }

    @Override
    public void run() {
        System.out.println("Consumer Thread " + Thread.currentThread().getName());
        app03SharedResource.consumeItem();
    }

}

public class App03_SharedResource {
    boolean itemAvailable = false;

    // synchronized put the object level lock
    public synchronized void produceItem() {
        itemAvailable = true;
        System.out.println("Item added by " + Thread.currentThread().getName() + " and invoking all threads which are waiting");
        notifyAll();
    }

    public synchronized void consumeItem() {
        System.out.println("Consume item method invoked by " + Thread.currentThread().getName());

        while (!itemAvailable) {
            try {
                System.out.println("Thread " + Thread.currentThread().getName() + " is waiting now");
                wait(); // releases the lock
            } catch (Exception e) {
                // handle exception
            }
        }
        System.out.println("Item consumed by " + Thread.currentThread().getName());
        itemAvailable = false;
    }

    public static void main(String[] args) {
        App03_SharedResource obj = new App03_SharedResource();


        Thread producerThread = new Thread(new ProduceItem(obj));
        Thread consumerThread = new Thread(new ConsumeItem(obj));
        consumerThread.start();
        producerThread.start();




    }
}
