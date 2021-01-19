package concurrent.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author L_MaFia
 * @classname ThirdThread.java
 * @description TODO
 * @date 2020/11/30
 */
public class ThirdThread implements Callable<Integer> {
    private final static int MAX = 50;
    private int i = 0;

    @Override
    public Integer call() {
     for(; i < MAX ; i++){
         System.out.println(Thread.currentThread().getName() + "_" + Thread.currentThread().getId() + "_"  + "_" + i);
     }
    return i;
    }


    public static void main(String[] args) {
        FutureTask task = new FutureTask<Integer>(new ThirdThread());
        FutureTask task1 = new FutureTask<Integer>(new ThirdThread());

        for (int i=0; i<MAX;i++){
            System.out.println(Thread.currentThread().getName() + Thread.currentThread().getId() + "+" + i);
            if (i == 20) {

                Thread firstThread = new Thread(task,"thread1");
                firstThread.start();

                Thread secondThread = new Thread(task1,"thread2");
                secondThread.start();
            }
        }
        try{
            System.out.println("thread1 return: " + task.get());
            System.out.println("thread2 return: " + task1.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
