package multithreading.threadintercommunication_05;

public class App01_HotstarApp {
    boolean isMatchLive = false;

    public synchronized void startMatch() throws InterruptedException {
        for (int i = 0; i <= 5; i++) {
            System.out.println("Match starting soon . . .");
            Thread.sleep(1000);
        }
        isMatchLive = true;
        notifyAll();
    }

    public synchronized void watchMatch() throws InterruptedException {
        while (!isMatchLive) {
            System.out.println("Waiting for match to begin " + Thread.currentThread().getName());
            wait();
        }
        for (int i = 0; i <= 5; i++) {
            System.out.println("Match is live now . . ." + Thread.currentThread().getName());
            Thread.sleep(1000);
        }
        System.out.println("Match ended");
        System.out.println("New match being soon");
        isMatchLive = false;
    }

    public static void main(String[] args) {
        App01_HotstarApp obj = new App01_HotstarApp();

        Thread userThread = new Thread(() -> {
            try {
                obj.watchMatch();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread adminThread = new Thread(() -> {
            try {
                obj.startMatch();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        userThread.start();
        adminThread.start();
    }
}
