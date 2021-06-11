package Lab1;

import java.util.*;
import java.util.ArrayList;
class Counter extends Thread
{
    private int from, to, step;
    public   ArrayList<Integer> tablou;

    public Counter(ArrayList<Integer> tablou, int from, int to, int step){
        this.from = from;
        this.to = to;
        this.step = step;
        this.tablou = tablou;
    }

    public void run() {
        int s , p1 , p2 ;
        int i = from;

        while(i != to){
           // if ((i % 2) != 0) {
            if (from == 0){
                p1 = tablou.get(i)*tablou.get(i + 2);
                p2 = tablou.get(i + 4)*tablou.get(i + 6);
                s = p1 + p2;
                System.out.println(getName() + " 'P1' "+ p1 +" + 'P2' "+ p2 +" = 'S' "+ s);
                i += 8;
            }
            else /*(i >= from)*/ {
                p1 = tablou.get(i)*tablou.get(i - 2);
                p2 = tablou.get(i - 4)*tablou.get(i - 6);
                s = p1 + p2;
                System.out.println(getName() + " 'P1' "+ p1 +" + 'P2' "+ p2 +" = 'S' "+ s);
                i -= 8;
        }}}
    }

public class Main {
    public static void main(String args[])
    {
        ArrayList<Integer> tablou = new ArrayList<Integer>();
        String info = " Stefaniuc Nicolae ";
        char[] ch = new char[info.length()];

        for  (int i = 0; i < info.length(); i++){
            ch[i]=info.charAt(i);
        }

        for(int i= 0; i<100; i++){
            tablou.add(new Random().nextInt(100));
            System.out.print(tablou.get(i) + " ");
            if(i==50) System.out.println();
        }
        System.out.println();

        Counter Th1 = new Counter(tablou, 0, 91, 1);
        Thread beginningThread = new Thread(Th1, "se incepe");

        Counter Th2 = new Counter(tablou, 99,8,-1);
        Thread endThread = new Thread(Th2, "se termina");

        beginningThread.start();
        endThread.start();

    }
}