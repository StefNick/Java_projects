
import java.util.ArrayList;


class partOne extends Thread {
    String name1;
    public partOne( String name){
        name1 = name;
    }
    public void run() {
        int s , p1 , p2 ;
        int i = 19;
        System.out.println("@2222222222  "+ i);
        while(i != 119){

            p1 = (i)*(i + 2);

            p2 = (i + 4)*(i + 6);
            s = p1 + p2;
            System.out.println(getName() + " 'P1' "+ p1 +" + 'P2' "+ p2 +" = 'S' "+ s);
            i += 8;

        }
        System.out.println("1: Nicolae");
    }

}

class partTwo extends Thread {
    String name1;
    public partTwo( String name){
        name1 = name;
    }
    public void run() {
        int s , p1 , p2 ;
        int i = 106;

        while(i != 6){

            p1 = (i)*(i - 2);
            p2 = (i - 4)*(i - 6);
            s = p1 + p2;
            System.out.println(getName() + " 'P1' "+ p1 +" + 'P2' "+ p2 +" = 'S' "+ s);
            i -= 8;

        }
        System.out.println("2: Stefaniuc");
    }
}

class partThree extends  Thread {
    @Override
    public void run(){
        System.out.println("Starting Thread 3 ");
        for (int i = 654; i<= 1278; i++){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        while(second.isAlive()){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("4: RM-181");
    }

}

class partFour extends Thread {
    @Override
    public void run(){
        System.out.println("Starting Thread 4 ");
        for (int i = 908; i >= 123; i--){
            System.out.print(i + " ");
        }
        System.out.println(" ");
        while(first.isAlive()){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("3: PCD");
    }
}
public class Lab3 {
    static int size = 100;
    static int counter = 0;
    static int[] b = new int[size];
    static int[] a;

    //static partOne first = new partOne();
    //static partTwo second = new partTwo();
    static partThree third = new partThree();
    static partFour fourth = new partFour();


    //static int pairone = 0;
    //static int pairtwo = 0;

    public static void main(String[] args) {
        System.out.println("Printing Array: ");
        for (int i = 0; i< 100; i++) {
            b[i] = (int) Math.round((Math.random() * 100) + 15);
            System.out.print (b[i] + " ");
            if (i == 50){
                System.out.println (b[i] + " ");
            }

            if (b[i]%2==0){
                counter++;
            }
        }
        a = new int[counter+1];
        int k = 0;
        for (int i = 0; i< 100; i++){
            if (b[i]%2==0){
                a[k] = b[i];
                k++;
            }
        }
        partOne first = new partOne("unu");
        partTwo second = new partTwo("doi");
        first.start();
        second.start();
        third.start();
        fourth.start();
        try{
            first.join();
            second.join();
            third.join();
            fourth.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
