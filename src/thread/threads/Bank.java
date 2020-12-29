package thread.threads;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author L_MaFia
 * @classname Bank.java
 * @description TODO
 * @date 2020/12/29
 */
public class Bank {
    private final double[] accounts;
    private ReentrantLock lock = new ReentrantLock();

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
    }

    public void transfer(int from, int to, double amount) {
        lock.lock();
        try {
            //如果return就不会经过finally,导致死锁
//        if (accounts[from] < amount) {
////            System.out.println("the account is insufficient");
//            return;
//        }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f\n", getTotalBalance());
        } finally {
            lock.unlock();
        }
    }

    public double getTotalBalance() {
        double sum = 0.0;
        for (double account : accounts) {
            sum += account;
        }
        return sum;
    }

    public int getAccountsSize() {
        return accounts.length;
    }
}
