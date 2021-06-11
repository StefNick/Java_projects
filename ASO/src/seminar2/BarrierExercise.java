package seminar2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class BarrierExercise extends Thread {

    CyclicBarrier cyclicBarrier;

    public BarrierExercise(CyclicBarrier cyclicBarrier, String name) {
        super(name);
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Studentul " + Thread.currentThread().getName() + " s-a inscris la cursuri");
            //Thread.sleep(500);
            //cyclicBarrier.await();

            System.out.println("Studentul " + Thread.currentThread().getName() + " trece cursul");
            Thread.sleep(7000);
            cyclicBarrier.await();
            System.out.println("Studentul " + Thread.currentThread().getName() + " primeste diploma");
        } catch(InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        BarrierExercise barrierExercise1 = new BarrierExercise(cyclicBarrier, "Student 1");
        BarrierExercise barrierExercise2 = new BarrierExercise(cyclicBarrier, "Student 2");
        BarrierExercise barrierExercise3 = new BarrierExercise(cyclicBarrier, "Student 3");

        barrierExercise1.start();
        barrierExercise2.start();
        barrierExercise3.start();
    }
}
