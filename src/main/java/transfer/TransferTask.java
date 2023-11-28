package transfer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 这个平台来进行转账操作
 *
 * @author keboom
 * @date 2021/8/24
 */
public class TransferTask {

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account(1L, 100000);
        Account accountB = new Account(1L, 100000);

        ExecutorService toA = Executors.newFixedThreadPool(3);
        ExecutorService toB = Executors.newFixedThreadPool(3);


        for (int i = 0; i < 1000; i++) {
            toB.execute(() -> {
                accountB.transfer(accountA, 10);
            });
        }

        for (int i = 0; i < 1000; i++) {
            toA.execute(() -> {
                accountA.transfer(accountB, 10);
            });
        }

        toA.shutdown();
        toB.shutdown();

        toA.awaitTermination(1, TimeUnit.MINUTES);
        toB.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("accountA: " + accountA.getBalance()+ "  " + accountA.count);
        System.out.println("accountB: " + accountB.getBalance()+ "  " + accountB.count);

    }
}
