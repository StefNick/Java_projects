package Lab2.Nick;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

class Proccess extends Thread{
    Proccess proc = new Proccess();

}

class TimerTask1 extends TimerTask {
    JLabel currentDateTime;
    public TimerTask1(JLabel aLabel){
        this.currentDateTime = aLabel;
    }
    @Override
    public void run() {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        DateFormat dateandtime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        Date date = new Date();
                        currentDateTime.setText(dateandtime.format(date));
                    }
                }
        );
    }
}

class TimeTask {
public static void main(String[] args) {
    Timer timer = new Timer();
    JLabel label = new JLabel();
    timer.schedule(new TimerTask1(label), 0, 1000);


        }
    }