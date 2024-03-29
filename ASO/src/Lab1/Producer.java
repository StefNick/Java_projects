package Lab1;

public class Producer extends Thread {
    private CubbyHole cubbyhole;
    private int number;

    public Producer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            cubbyhole.put(i);
            System.out.println("Producer #" +
                    this.number + " put: " + i);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
class Consumer extends Thread {
    private CubbyHole cubbyhole;
    private int number;
    public Consumer(CubbyHole c, int number) {
        cubbyhole = c;
        this.number = number;
    }
    public void run() {
        int value = 0;
        for (int i = 0; i < 10; i++) {
            value = cubbyhole.get();
            System.out.println("Consumer #" +
                    this.number + " got: " + value);
        }
    }
}
class ProducerConsumerTest {
    public static void main(String[] args) {
        CubbyHole c = new CubbyHole();
        Producer p1 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);
        p1.start();
        c1.start();
    }
}
class CubbyHole {
    private int number = -1;
    private boolean available = false;
    public synchronized int get() {
        while (!available) {
            try {
                wait();
                //aşteaptă producatorul să pună o valoare
            } catch (InterruptedException e) { }
        }
        available = false;
        notifyAll();
        return number;
    }
    public synchronized void put(int number) {
        while (available) {
            try {
                wait();
                //aşteaptă consumatorul să preia valoarea
            } catch (InterruptedException e) { }
        }
        this.number = number;
        available = true;
        notifyAll();
    }
}