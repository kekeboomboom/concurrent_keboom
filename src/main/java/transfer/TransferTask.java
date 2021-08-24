package transfer;

import java.math.BigDecimal;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 这个平台来进行转账操作
 *
 * @author keboom
 * @date 2021/8/24
 */
public class TransferTask implements Runnable {

    private Account from, to;
    private double money;

    public TransferTask(Account from, Account to, double money) {
        this.from = from;
        this.to = to;
        this.money = money;
    }

    @Override
    public void run() {
        transfer(from, to, money);
    }

    /**
     * 不考虑数据库，事务，线程池等，假设账户在本地
     *
     * @param from
     * @param to
     * @param money
     * @return
     */
    public void transfer(Account from, Account to, double money) {
        //为了防止死锁，我们需要根据id顺序加锁
        Account first = from.getId() < to.getId() ? from : to;
        Account second = from.getId() < to.getId() ? to : from;
        synchronized (first) {
            synchronized (second) {
                if (from.getBalance() - money >= 0) {
                    System.out.println(Thread.currentThread().getName() + " start: " + System.currentTimeMillis());
                    // 这个钱的操作应该得进事务吧。
                    BigDecimal bfrom = new BigDecimal(from.getBalance());
                    BigDecimal bmoney = new BigDecimal(money);
                    BigDecimal bto = new BigDecimal(to.getBalance());
                    from.setBalance(bfrom.subtract(bmoney).doubleValue());
                    to.setBalance(bto.add(bmoney).doubleValue());
                    System.out.println(Thread.currentThread().getName() + " end:   " + System.currentTimeMillis());

                } else {
                    throw new RuntimeException("余额不足");
                }
            }
        }

    }


    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        Account a1 = new Account(1L, 100);
        Account a2 = new Account(2L, 100);
        Account a3 = new Account(3L, 100);
        Account a4 = new Account(4L, 100);

        TransferTask task = new TransferTask(a1, a2, 20.5);
        TransferTask task2 = new TransferTask(a3, a4, 20.5);


        executor.execute(task);
        executor.execute(task2);

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("a1:" + a1.getBalance());
        System.out.println("a2:" + a2.getBalance());
        System.out.println("a3:" + a3.getBalance());
        System.out.println("a4:" + a4.getBalance());
    }
}
