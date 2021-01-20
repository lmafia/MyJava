package concurrent.forkJoin;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * @author L_MaFia
 * @classname ForkJoinTest.java
 * @description 计算密集型任务 使用 fork json 模式
 * 处理非耗时任务可以使用该模式
 * @date 2021/1/21
 */
public class ForkJoinTest {
    private final static int SIZE = 10000000;

    public static void main(String[] args) {
        Long nowTime = System.currentTimeMillis();
        double[] numbers = new double[SIZE];
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = Math.random();
        }

        Counter counter = new Counter(numbers, 0, numbers.length, x -> x > 0.5);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(counter);
        System.out.println(counter.join());
        Long endTime = System.currentTimeMillis();
        System.out.println("run time：" + (endTime - nowTime) + " ms");
    }

    static class Counter extends RecursiveTask<Integer> {
        public static final int THRESHOLD = 1000000;
        private double[] values;
        private int from;
        private int to;
        private DoublePredicate filter;

        public Counter(double[] values, int from, int to, DoublePredicate filter) {
            this.values = values;
            this.from = from;
            this.to = to;
            this.filter = filter;
        }

        @Override
        protected Integer compute() {
            if (to - from < THRESHOLD) {
                int count = 0;
                for (int i = from; i < to; i++) {
                    if (filter.test(values[i])) {
                        count++;
                    }
                }
                return count;
            } else {
                int mid = (from + to) / 2;
                Counter firstCounter = new Counter(values, from, mid, filter);
                Counter secondsCounter = new Counter(values, mid, to, filter);
                invokeAll(firstCounter, secondsCounter);
                return firstCounter.join() + secondsCounter.join();
            }
        }


    }

}
