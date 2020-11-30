
# Thread 
### 创建线程方式1
继承Thread类,并重写run方法。

* 指定线程名字，线程名可以一样，线程是根据线程号来区分的。
* 这种创建方式，线程之间不会共用资源，因此下面创建的线程是各自把i自增到99。
```java
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
```

### 创建线程方式2
实现Runnable接口,并重写run方法。
* 两个线程共用一个Runnable对象：
* 这种创建方式，线程之间会共用资源，因此下面创建的线程共同把i自增到99(除非各自有各自的Runnable对象)。
```java
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
```

### 创建线程方式3
实现Callable接口,并重写run方法。
* 可以带有返回值
* 需要使用FutureTask类包装Callable对象，为了封装Callable的call方法的返回值。
* 使用FutureTask的对象作为Thread的target，2个线程不能使用同一个task，需要创建2个。
* 这种创建方式，线程之间会共用资源，因此下面创建的线程共同把i自增到99(除非各自有各自的Callable对象和FutureTask对象)。
```java
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
```