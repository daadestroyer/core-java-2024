package multithreading.problem_with_synchronized_06;

class SharedResource {
    public synchronized void producer() {
        try {
            System.out.println("Lock acquired by : " + Thread.currentThread().getName());
        } catch (Exception e) {
            // handle exception here
        }
        System.out.println("Lock release by :" + Thread.currentThread().getName());
    }
}

public class App01_ProblemWithSynchronized {
    public static void main(String[] args) {
        SharedResource sharedResource1 = new SharedResource();
        SharedResource sharedResource2 = new SharedResource();
        Thread t1 = new Thread(() -> sharedResource1.producer(), "Thread 1");
        Thread t2 = new Thread(() -> sharedResource2.producer(), "Thread 2");

        t1.start();
        t2.start();

        // problem here is on two shared resource both threads acquired lock at same time
        // and we are unable to achieve synchronization

    }
}
