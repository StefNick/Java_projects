package seminar;


import java.awt.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class Sound extends TimerTask{
    @Override
    public void run(){
        Toolkit.getDefaultToolkit().beep();
        System.out.println("Sound played");
    }
}

class Message extends TimerTask{
    String msg;

    public Message(String msg){
        this.msg=msg;
    }

    @Override
    public void run(){
        System.out.println(msg);
    }
}

public class TimeApp {
    public static void main(String[] args) {
        Sound sound = new Sound();
        Message message = new Message("hello world");

        Timer soundTimer = new Timer();
        Timer messageTimer = new Timer();
        Timer clock = new Timer();


        soundTimer.scheduleAtFixedRate(sound,0,2000);
        messageTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Au trecut 5 secunde");
                soundTimer.cancel();
            }}, 5000);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 19);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();

        clock.schedule(message, date);
    }
}
