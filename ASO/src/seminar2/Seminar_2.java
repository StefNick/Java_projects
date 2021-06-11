package seminar2;

import java.util.concurrent.Semaphore;

public class Seminar_2 {

    private final static int controlPoints = 3;
    private final static int maxStudents = 10;
    private static boolean[] occupied = null;
    private static Semaphore semaphore = null;

    public static class SemaphoreThread implements Runnable {
        private final int studentIndex;

        public SemaphoreThread(int studentCount) {
            this.studentIndex = studentCount;
        }

        @Override
        public void run() {
            int semaphoreCounter = -1;
            try {
                semaphore.acquire();
                synchronized(occupied) {
                    for(int i = 0; i < occupied.length; i++) {
                        if(occupied[i] == true) {
                            occupied[i] = false;
                            semaphoreCounter = i;
                            System.out.println("Studentul " + studentIndex + " s-a apropiat de punctul de control.\n");
                            break;
                        }
                    }
                }
                Thread.sleep(5000);

                synchronized(occupied) {
                    occupied[semaphoreCounter] = true;
                }
                semaphore.release();
                System.out.println("Studentul " + studentIndex + " a finisat controlul.\n");

            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        occupied = new boolean[controlPoints];
        for(int i = 0; i < occupied.length; i++) {
            occupied[i] = true;
            System.out.println(occupied[i]);
        }
        semaphore = new Semaphore(occupied.length, true);
        for(int i = 1; i <= maxStudents; i++) {
            new Thread(new SemaphoreThread(i)).start();
        }
    }
}
