package thread.threads;

/**
 * @author L_MaFia
 * @classname SecondThread.java
 * @description TODO
 * @date 2020/11/30
 */
public class SecondThread implements Runnable {
    private final static int MAX = 100;
    private int i = 0;
    @Override
    public void run() {
        for (; i < MAX; i++) {
            System.out.println(Thread.currentThread().getName() + "_" + Thread.currentThread().getId() + "_"  + "_" + i);
        }


    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "+" + i);
            if (i == 20) {
                SecondThread target = new SecondThread();
                SecondThread target1 = new SecondThread();
                Thread firstThread = new Thread(target,"thread1");
                firstThread.start();

                Thread secondThread = new Thread(target,"thread1");
                secondThread.start();

                Thread thirdThread = new Thread(target1,"thread1");
                thirdThread.start();

            }
        }
    }
}