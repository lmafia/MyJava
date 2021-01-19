package concurrent.threads;

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
//            System.out.println(getState());

            System.out.println(getName() + '_' + getState());


        }
        
        

    }


    public static void main(String[] args) {
        FirstThread firstThread = null;
        FirstThread secondThread = null;
        for (int i = 0; i < MAX; i++) {
            System.out.println(currentThread().getName() + currentThread().getId() + "+" + i);

            if (i == 20) {
                 firstThread = new FirstThread();
                firstThread.setName("thread1");
                System.out.println(firstThread.getState());
                firstThread.start();
                System.out.println(firstThread.getState());

                System.out.println(currentThread().getName() +  Thread.currentThread().getState());
                System.out.println(firstThread.getName() +  firstThread.getState());
                secondThread = new FirstThread();
                secondThread.setPriority(10);
                secondThread.setName("thread2");
                secondThread.start();
//                FirstThread thirdThread = new FirstThread();
//                thirdThread.setName("thread1");
//                thirdThread.start();
            }
            if( i >= 20){
                
                System.out.println(firstThread.getName() + firstThread.getState());
            }
            if (i >= 30){

                try {
                    assert secondThread != null;
                    secondThread.join();
                    firstThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


