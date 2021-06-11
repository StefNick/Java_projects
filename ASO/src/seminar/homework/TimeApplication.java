//homework
//de programat un timer, care o sa numere pana la 10 cu intevalul de 1 secunda, trebuie de il terminat
//inca un timer care, se va restarta la fiecare 10 secunde

package seminar.homework;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


class TimerTest extends TimerTask{
    private int counter = 0;

    @Override
    public void run(){
        System.out.println("Lets do this " + ++counter);
        if(counter == 10){
            synchronized (TimeApplication.test){
                TimeApplication.test.notify();
            }
        }
    }
}

class Sound extends TimerTask{
    @Override
    public void run(){
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Sound played");
    }
}
class Message extends TimerTask {
    String msg;

    public Message(String msg){
        this.msg=msg;
    }

    @Override
    public void run(){
        System.out.println(msg);
    }
}


public class TimeApplication {
    static TimeApplication test;
    public static void main(String[] args){

        Sound sound = new Sound();
        Message message = new Message("Restart: ");

        Timer soundTimer = new Timer();
        Timer messageTimer = new Timer();
        Timer clock = new Timer();
        test = new TimeApplication();



        soundTimer.scheduleAtFixedRate(sound, 0, 3000);
        messageTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(message.msg);
                        soundTimer.cancel();}},1000);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 11);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();

        clock.scheduleAtFixedRate(message, date, 1000);
    }
}



