package multithreading;


import java.util.LinkedList;
import java.util.Queue;

class SharedResource {
    private Queue<Integer> sharedBuffer;
    private int bufferSize;

    public SharedResource(int bufferSize) {
        this.sharedBuffer = new LinkedList<>();
        this.bufferSize = bufferSize;
    }

    public synchronized void produceItem(int item) throws InterruptedException {
        while (sharedBuffer.size() == bufferSize) {
            System.out.println("Producer Thread " + Thread.currentThread().getName() + " is waiting to produce item");
            wait();
        }
        sharedBuffer.add(item);
        System.out.println("Item added : " + item);
        notifyAll();
    }

    public synchronized void consumeItem() throws InterruptedException {
        while (sharedBuffer.isEmpty()) {
            System.out.println("Consumer Thread " + Thread.currentThread().getName() + "is waiting to consume");
            wait();
        }
        System.out.println("Consumed Item : " + sharedBuffer.poll());
        notifyAll();
    }
}

public class ProducerConsumerSharedResource {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource(3);

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i <= 6; i++) {
                    sharedResource.produceItem(i);
                }
            } catch (Exception e) {
                // handle exception
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                sharedResource.consumeItem();
            } catch (Exception e) {
                // handle exception
            }
        });


        producerThread.start();
        consumerThread.start();
    }
}
