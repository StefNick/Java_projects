package seminar;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class TimerTest extends TimerTask{
    private int counter = 0;

    @Override
    public void run()
    {
        System.out.println("Timer is running " + ++counter);
        if(counter == 5){
            synchronized (Main.test){
                Main.test.notify();
            }
        }
    }
}

public class Main {
    static Main test;
    public static void main(String[] args){

        Timer timer = new Timer();
        test = new Main();
        Date date = new Date();

        timer.scheduleAtFixedRate(new TimerTest(), date, 3000);
        synchronized (test){
            try{test.wait(); }catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }
            timer.cancel();
            timer.purge();
        }
    }
}
