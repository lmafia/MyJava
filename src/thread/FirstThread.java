package thread;

/**
 * @author L_MaFia
 * @classname FirstThread.java
 * @description TODO
 * @date 2020/11/30
 */
public class FirstThread extends Thread {

    private final static int MAX = 100;
    private int i = 0;
    @Override
    public void run() {
        for (; i < MAX; i++) {
            System.out.println(getName() + "_" + getId() + "_" + getPriority() + "_" + i);
        }

    }


    public static void main(String[] args) {
        for (int i = 0; i < MAX; i++) {
            System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "+" + i);
            if (i == 20) {
                FirstThread firstThread = new FirstThread();
                firstThread.setName("thread1");
                firstThread.start();
                FirstThread secondThread = new FirstThread();
                secondThread.setName("thread1");
                secondThread.start();
//                FirstThread thirdThread = new FirstThread();
//                thirdThread.setName("thread1");
//                thirdThread.start();
            }
        }
    }
}


