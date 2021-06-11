package Lab2Nick;

public class Lab2 {
        public static void main(String[] args) {
        ThreadGroup sys = Thread.currentThread().getThreadGroup();



        Thread curr = Thread.currentThread();
        curr.setPriority(curr.getPriority() + 1);
        Thread th1 = new Thread( "Th1");
        th1.setPriority(3);
        th1.start();

        sys.list();

        ThreadGroup G1 = new ThreadGroup("G1");

        Thread thA = new Thread(G1, "ThA");
        thA.setPriority(3);
        thA.start();

        G1.list();

        ThreadGroup G3 = new ThreadGroup(G1, "G3");

                Thread thf = new Thread(G3, "Thf");
                thf.setPriority(3);
                thf.start();

                Thread thb = new Thread(G3, "Thb");
                thb.setPriority(7);
                thb.start();

                Thread thc = new Thread(G3, "Thb");
                thc.setPriority(3);
                thc.start();

                Thread thd = new Thread(G3, "Tha");
                thd.setPriority(3);
                thd.start();

                G3.list();

                ThreadGroup G2 = new ThreadGroup(G1, "G2");

                Thread th8 = new Thread(G2, "Th8");
                th8.setPriority(3);
                th8.start();

                Thread th9 = new Thread(G2, "Th9");
                th9.setPriority(4);
                th9.start();

                Thread th3 = new Thread(G2, "Th3");
                th3.setPriority(3);
                th3.start();

                G2.list();


        }}

