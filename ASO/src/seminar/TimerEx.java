package seminar;


import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/*class Message1 extends TimerTask {
    String msg;

    public Message1(String msg){
        this.msg=msg;
    }

    @Override
    public void run(){
        System.out.println(msg);
    }
}*/
public class TimerEx {
    public static void main(String[] args) {

        Message message = new Message("Salut");

        Timer clock = new Timer();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 6);
        calendar.set(Calendar.SECOND, 0);

        Date date = calendar.getTime();

        clock.scheduleAtFixedRate(message, date,3000 );

    }
}
//homework
//de progrmat un timer, care o sa numere pana la 10 cu intevalul de 1 secunda, trebuie de il terminat
//inca un timer care, se va restarta la fiecare 10 secunde