package Lab2;

public class Main {
    public static void createMultipleThreads(ThreadGroup parent, int amount, char start, int... priorities) {
        int asciiValue = (int)start;
        for (int i = asciiValue, p = 0; i < asciiValue + amount; i++, p++) {
            assert priorities[p] >= Thread.MIN_PRIORITY && priorities[p] <= Thread.MAX_PRIORITY : "Invalid priority";
            String name = "Th" + (char)i;
            Thread thread = new Thread(parent, name);
            thread.setPriority(priorities[p]);
            thread.start();
        }
    }
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup group2 = new ThreadGroup(mainGroup, "G2");
        ThreadGroup group1 = new ThreadGroup(group2, "G1");
        ThreadGroup group3 = new ThreadGroup(mainGroup, "G3");

        createMultipleThreads(group1, 4, 'a', 1, 3, 8, 3);
        createMultipleThreads(group2, 1, 'A', 1);
        createMultipleThreads(group3, 3, '1', 4, 3, 5);
        createMultipleThreads(mainGroup, 2, '1', 3, 6);
        mainGroup.list();
    }
}