package concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author keboom
 * @date 2021/8/24
 */
public class CountTest {


//    private AtomicInteger count = new AtomicInteger(0);
//
//    public void add() {
//        count.getAndIncrement();
//    }
//
//    public int getCount() {
//        return count.get();
//    }


    private int count = 0;

    public void add() {
        count++;
    }

    public int getCount() {
        return count;
    }


//    private int count = 0;
//
//    public synchronized void add() {
//        count++;
//    }
//
//    public int getCount() {
//        return count;
//    }
}
