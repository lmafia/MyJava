package thread.bankThreads;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author L_MaFia
 * @classname Bank.java
 * @description TODO
 * @date 2020/12/29
 */
public class Bank {
    private final double[] accounts;
    /**
     * 锁
     */
    private ReentrantLock lock;
    /**
     * 条件对象 。足够金额条件
     */
    private Condition sufficientFunds;

    public Bank(int n, double initialBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        lock = new ReentrantLock();
        sufficientFunds = lock.newCondition();
    }

    public void transfer(int from, int to, double amount) throws InterruptedException {
        lock.lock();
        try {
            //如果return就不会经过finally,导致死锁
//        if (accounts[from] < amount) {
////            System.out.println("the account is insufficient");
//            return;
//        }
            //但如果账户不够钱了咋办呢？线程获得锁了进入临界区，但是线程还没不能继续跑
            //需要引入条件对象，和锁斤进行关联。
            while (accounts[from] < amount) {
//            System.out.println("the account is insufficient");
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf("Total Balance: %10.2f\n", getTotalBalance());
            sufficientFunds.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void transferWithSync(int from, int to, double amount) throws InterruptedException {
        while (accounts[from] < amount){
            wait();
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        accounts[to] += amount;
        System.out.printf("Total Balance: %10.2f\n", getTotalBalanceWithSync());
        notify();
    }

    public double getTotalBalance() {
        lock.lock();
        try {
            double sum = 0.0;
            for (double account : accounts) {
                sum += account;
            }
            return sum;

        } finally {
            lock.unlock();
        }
    }

    public synchronized double getTotalBalanceWithSync() {
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
