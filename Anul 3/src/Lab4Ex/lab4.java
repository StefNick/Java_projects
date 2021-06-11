package Lab4Ex;

import java.util.LinkedList;

public class lab4 {
    public static void main(String[] args)
    {
        Connecter c = new Connecter();
        Producer p1 = new Producer(c,1);
        Consumer c1 = new Consumer(c,1);
        Consumer c2 = new Consumer(c,2);
        Producer p2 = new Producer(c,2);
        Producer p3 = new Producer(c,3);
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        while(c.getk() > 0){
            System.out.println(" k= "+c.getk()) ;
            if(c.getk() == 0) {
                System.out.println(" k2= "+ c.getk()) ;
                p1.stop();
                p2.stop();
                p3.stop();
                c1.stop();
                c2.stop();
                System.out.println(" programul sa finalizat ") ;
            }

        }
    }
    // This class has a list, producer (adds items to list
    // and consumber (removes items).



}
class Connecter {
    private LinkedList<Character> deposit;
    private boolean available = false;
    int full=47;
    int k=full;
    public Connecter() {
        deposit = new LinkedList<>();
    }
    public int getk(){
        return k;
    }
    public synchronized Character consume() {
        while (deposit.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) { }
            available = false;
            notifyAll();

        }
        if(k<=0){
            System.out.println(
                    "Thread was interrupted, Failed to complete operation");
            // Thread.currentThread().interrupt();

        }
        Character c = deposit.getFirst();
        deposit.removeFirst();
        k--;
        return c;
    }
    public synchronized void put(Character c) {
        while (deposit.size() >= 7) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if(deposit.size()<11){
            deposit.add(c);
            available = true;
            notifyAll();
            System.out.println("cat de implut e depozitul " +  deposit.size()) ;
        }
    }
}
class Producer extends Thread {
    private Connecter connect;
    private int c;
    public Producer(Connecter connect, int c) {
        this.connect = connect;
        this.c = c;
    }
    public void run() {
        Character[] vocale = {'a','e','i','o','u','y'};
        for (int i = 0; i < 47; i++) {
            int index = (int) (Math.random()*6);
            connect.put(vocale[index]);
            System.out.println("Producer #" + this.c + " put: " + vocale[index]);
            index = (int) (Math.random()*6);
            connect.put(vocale[index]);
            System.out.println("Producer #" + this.c + " put: " + vocale[index]);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
            }
        }
    }
}
class Consumer extends Thread {
    private Connecter connect;
    private int c;
    public Consumer(Connecter connect, int c) {
        this.connect = connect;
        this.c = c;
    }
    public void run() {
        for (int i = 0; i < 47; i++) {
            Character ch = connect.consume();
            System.out.println("Consumer #" + this.c + " got: " + ch);
        }
    }
}