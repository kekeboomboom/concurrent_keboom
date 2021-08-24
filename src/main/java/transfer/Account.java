package transfer;

/**
 * @author keboom
 * @date 2021/8/24
 */
public class Account {

    private Long id;
    private double balance;

    public Account(Long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
