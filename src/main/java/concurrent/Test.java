package concurrent;

/**
 * @author keboom
 * @date 2021/8/24
 */
public class Test {

    public static void main(String[] args) {
        CountTest countTest = new CountTest();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i =0;
                while (i < 100000000) {
                    countTest.add();
                    i++;
                }
                System.out.println(Thread.currentThread().getName()+":"+countTest.getCount());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i =0;
                while (i < 100000000) {
                    countTest.add();
                    i++;
                }
                System.out.println(Thread.currentThread().getName()+":"+countTest.getCount());
            }
        });

        long pre = System.currentTimeMillis();
        t1.start();
        t2.start();
        // wait unit all the threads have finished
        while(t1.isAlive() || t2.isAlive()) {}
        long cur = System.currentTimeMillis();
        System.out.println("time: "+ (cur-pre));

    }
}
