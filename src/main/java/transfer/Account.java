package transfer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author keboom
 * @date 2021/8/24
 */
public class Account {

    private Long id;
    private volatile double balance;
    private ReentrantLock lock = new ReentrantLock();
    // 记录转账次数，没什么用，只是为了验证transfer执行的次数
    AtomicInteger count = new AtomicInteger(0);

    public Account(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void deposit(double amount) {
        lock.lock();
        balance += amount;
        lock.unlock();
    }

    public void withdraw(double amount) {
        lock.lock();
        balance -= amount;
        lock.unlock();
    }

    public double getBalance() {
        return balance;
    }

    public boolean transfer(Account target, double amount) {
        if (this == target) {
            System.out.println("不能自己转给自己");
            return false; // 防止自己向自己转账
        }

        if (this.getBalance() < amount) {
            System.out.println("余额不足，转账失败");
            return false; // 余额不足，转账失败
        }
        // avoid deadlock
        // 使用两把锁，按照账户的 hashcode 顺序来避免死锁
        Account firstLock = this.hashCode() > target.hashCode() ? this : target;
        Account secondLock = this.hashCode() > target.hashCode() ? target : this;
        boolean flag = true;
        while (flag) {
            if (firstLock.lock.tryLock()) {
                try {
                    if (secondLock.lock.tryLock()) {
                        try {
                            this.withdraw(amount);
                            target.deposit(amount);
                            flag = false;
                            count.incrementAndGet();
                        } finally {
                            secondLock.lock.unlock();
                        }
                    }
                } finally {
                    firstLock.lock.unlock();
                }
            }
        }
        return true;
    }

}
