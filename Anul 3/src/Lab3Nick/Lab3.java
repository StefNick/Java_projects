package Lab3Nick;

    class partOne extends Thread {

        public partOne() { }

        public void run() {
            int pairone = 0;
            int pairtwo = 0;
            int result;
            int[] a = new int[0];
            int i = 19;
            System.out.println("Starting Thread 1 ");
            System.out.println(" Step1 ");

            while(i != 119){
                System.out.println(" step2 ");
                pairone = a[i] + a[i + 2];
                System.out.println(" step3 ");
                pairtwo = a[i + 4] + a[i + 6];
                System.out.println(" step4 ");
                result = pairone + pairtwo;
                System.out.println(" step5 ");
                System.out.println(getName() + "Current value for Thread 1 is: " + pairone + " + " + pairtwo + " = " + result);
                System.out.println(" step6 ");
            }

           Thread patru = null;
            while(patru.isAlive()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("1: Nicolae");
        }


    }
     class partTwo extends Thread {

         public partTwo() { }

         public void run() {
            int pairone = 0;
            int pairtwo = 0;
            int result;
            int[] a = new int[0];
            int i = 106;
            System.out.println("Starting Thread 2 ");
            while(i != 6){
                pairone = a[i] + a[i - 2];
                pairtwo = a[i - 4] + a[i - 6];
                result = pairone + pairtwo;
                System.out.println(Thread.currentThread().getName() + "Current value for Thread 1 is: " + pairone + " + " + pairtwo + " = " + result);
            }

            Thread trei = null;
            while(trei.isAlive()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("2: Stefaniuc");
        }

         public void start() {
         }


     }

     class partThree extends Thread {
         public partThree() {

         }

         public void run(){
            System.out.println("Starting Thread 3 ");
            for (int i = 654; i<= 1278; i++){
                System.out.print(i + " ");
            }
            System.out.println(" ");

            Thread doi = null;
            while(doi.isAlive()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("3: RM-181");
        }

         public void start() {
         }


     }
    class partFour extends Thread {
        public partFour() {

        }

        public void run(){
            System.out.println("Starting Thread 4 ");
            for (int i = 908; i >= 123; i--){
                System.out.print(i + " ");
            }
            System.out.println(" ");

            Thread unu = null;
            while(unu.isAlive()){
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("4: PCD");
        }

        public void start() {
        }


    }
    public class Lab3 {

  /*    public static int pairone = 0;
        public static int pairtwo = 0;
        public static int result;
*/
        public static void main(String[] args) {
        int size = 100;
        int counter = 0;
        int[] b = new int[size];
        int[] a;
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

       // Runnable task1 = new partOne(b);
           // Runnable task2 = new partTwo(b);
            //Runnable task3 = new partThree(b);
            //Runnable task4 = new partFour(b);

        partOne unu = new partOne();
        partTwo doi = new partTwo();
        partThree trei = new partThree();
        partFour patru = new partFour();





//partOne first = new partOne("unu");
//partTwo second = new partTwo("doi");
//partThree third = new partThree("trei");
//partFour fourth = new partFour("patru");
           // System.out.println(" Stepx ");
        unu.start();
           // System.out.println(" Stepy ");
        doi.start();
        trei.start();
        patru.start();

        //unu.join();
        //doi.join();
       // trei.join();
        //patru.join();

    }
}
