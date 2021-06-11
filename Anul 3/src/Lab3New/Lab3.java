package Lab3New;

import java.util.*;

class Th1 implements Runnable {
    private int[] array1 = new int[101];
    public Th1(int a[]) {
        array1 = a;
    }
    public void run() {
        int s = 0;
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] % 2 == 0) {
                if (s == 0) {
                    s += i;
                } else {
                    s += i;
                    System.out.print("In "+Thread.currentThread().getName()+": " + s+" ");
                    s = 0;
                }
            }
        }
        try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
        Thread.currentThread().suspend();
        String u = "Stefaniuc";
        System.out.print(Thread.currentThread().getName()+"  ");
        for(int j=0; j<=u.length(); j++){
            System.out.print(u.charAt(j));
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
        }
    }
}
class Th2 implements Runnable {
    private int[] array2 = new int[100];
    public Th2(int a[]) {
        array2 = a;
    }
    public void run() {
        int s = 0;
        for (int i = array2.length - 1; i > -1; i--) {
            if (array2[i] % 2 == 0) {
                if (s == 0) {
                    s += i;
                } else {
                    s += i;
                    System.out.print("In "+Thread.currentThread().getName()+": " + s+" ");
                    s = 0;
                }
            }
        }
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
        String u = "Nicolae";
        System.out.print(Thread.currentThread().getName()+"  ");
        for(int j=0; j<=u.length(); j++){
            System.out.print(u.charAt(j));
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
        }
    }
}
class Th3 implements Runnable {
    private int[] array3 = new int[101];
    private int from2;
    private int to2;
    private int step2;
    public Th3(int from2, int to2, int step2, int ar1[]) {
        array3 = ar1;
        this.step2 = step2;
        this.from2 = from2;
        this.to2 = to2;
    }
    public void run() {
        for(int i=from2;i<=to2;i++) {
            System.out.print(Thread.currentThread().getName()+"; step2= " +step2+ " ;i[" +i+ "]= " +"  ");
        }
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}
        Thread.currentThread().suspend();
        String u = "Programare Concurenta si distribuitiva";
        System.out.print(Thread.currentThread().getName()+"  ");
        for(int j=0; j<=u.length(); j++){
        System.out.print(u.charAt(j));
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
    }}
}
class Th4 implements Runnable {
    private int[] array4 = new int[101];
    private int from1;
    private int to1;
    private int step1;
    public Th4(int from1, int to1, int step1, int ar[]) {
        array4 = ar;
        this.step1 = step1;
        this.from1 = from1;
        this.to1 = to1;
    }
    public void run() {
        for(int i=from1;i!=to1;i--) {
            System.out.print (Thread.currentThread().getName()+"; step1= " +step1+ " ;a[" +i+ "]= " +array4[i]+"   ");
        }
        try{Thread.sleep(500);}catch(InterruptedException e){System.out.println(e);}
        Thread.currentThread().suspend();
        String u = "RM-181";
        System.out.print(Thread.currentThread().getName()+"  ");
        for(int j=0; j<=u.length(); j++){
            System.out.print(u.charAt(j));
            try{Thread.sleep(100);}catch(InterruptedException e){System.out.println(e);}
        }
    }
}
public class Lab3 {
    public static void main(String[] args) {
        //
        int[] tablou = new int[1001];
        for (int i = 0; i < 1000; i++) {
            tablou[i] = (int)(Math.random() * 999);

        }
        System.out.println(" ");
        Runnable task1 = new Th1(tablou);
        Runnable task2 = new Th2(tablou);
        Runnable task3 = new Th3(654,1278,1,tablou);
        Runnable task4 = new Th4(908,123,-1,tablou);
        Thread treadA = new Thread(task1);
        Thread treadB = new Thread(task2);
        Thread treadC = new Thread(task3);
        Thread treadD = new Thread(task4);
        treadB.setName("Th2");
        treadA.setName("Th1");
        treadC.setName("Th3");
        treadD.setName("Th4");
        treadA.start();
        treadB.start();
        treadC.start();
        treadD.start();

        try{
            treadB.join();
            while(treadD.isAlive()){
                treadD.resume();
                Thread.sleep(100);
            }
            while(treadA.isAlive()){
                treadA.resume();
                Thread.sleep(100);
            }
            while(treadC.isAlive()){
                treadC.resume()
                ;
                Thread.sleep(100);
            }

        }catch(InterruptedException e){}
    }
}
