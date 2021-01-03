package thread.threads;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author L_MaFia
 * @classname SyncBankTest.java
 * @description 加锁的多线程demo
 * @date 2020/12/29
 */
public class SyncBankTest {
    private static final int ACCOUNTS = 10;
    private static final double INITIAL_BALANCE = 1000;
    private static final double MAX_AMOUNT = 1 * 1000;
    private static final int DELAY = 100;
    public static final ThreadLocal<Random> randomThreadLocal = ThreadLocal.withInitial(Random::new);

    public static void main(String[] args) {
        Bank bank = new Bank(ACCOUNTS, INITIAL_BALANCE);


        for (int i = 0; i < ACCOUNTS; i++) {
            int formAmount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.getAccountsSize() * ThreadLocalRandom.current().nextLong());
                        double amount = MAX_AMOUNT * Math.random();
//                        bank.transfer(formAmount, toAccount, amount);
                        bank.transferWithSync(formAmount, toAccount, amount);
                        Thread.sleep((long) (DELAY * randomThreadLocal.get().nextLong()));

                    }
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            };
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
