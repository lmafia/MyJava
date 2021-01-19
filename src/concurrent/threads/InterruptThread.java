package concurrent.threads;

/**
 * @author L_MaFia
 * @classname InterruptThread.java
 * @description TODO
 * @date 2020/12/28
 */
public class InterruptThread {


    public static void main(String[] args) {
        Runnable runnable = () -> {

            int i = 0;
            try {

                while (!Thread.interrupted() && i < 10) {
                    System.out.println(Thread.currentThread().getName() + i);
                    i++;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
                System.out.println("interrupt error");
            } finally {

            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(100);
        for (int i = 0 ; i< 100; i++){
            System.out.println(i);
//            if (i == 20){
//                System.out.println(Thread.interrupted());
//                concurrent.interrupt();
//            }
        }

    }


}
