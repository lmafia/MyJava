package thread.concurrentHashMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author L_MaFia
 * @classname ConcurrentHashMapTest.java
 * @description 统计java文件的所有单词出现次数
 * @date 2021/1/10
 */
public class ConcurrentHashMapTest {
    private static ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    public static int TARGET_TIMES = 10;

    private static void process(Path file) {
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                String word = in.next();
//                map.merge(word, 1L, (var1, var2) -> var1 + var2);
//                map.merge(word, 1L, Long::sum);
                map.compute(word, (k, v) -> v == null ? 1 : v + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<Path> descendants(Path rootDir) throws IOException {
//        Files.walk 遍历文件夹
        Stream<Path> entries = Files.walk(rootDir);
//        集合的转换
        return entries.collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
//        获取CPU核心数
        int processors = Runtime.getRuntime().availableProcessors();
//        创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(processors);
//        获取当前目录
        Path pathRoot = Paths.get(".");
        try {
//            遍历所有文件
            for (Path p : descendants(pathRoot)) {
//                获取java文件
                if (p.getFileName().toString().endsWith(".java")) {
                    executor.execute(() -> process(p));
                }
            }
//            如果要关闭ExecutorService中执行的线程，
//            我们可以调用ExecutorService.shutdown()方法。
//            在调用shutdown()方法之后，ExecutorService不会立即关闭，
//            但是它不再接收新的任务，直到当前所有线程执行完成才会关闭，
//            所有在shutdown()执行之前提交的任务都会被执行。
            executor.shutdown();
//            当前线程阻塞，
//            直到等所有已提交的任务（包括正在跑的和队列中等待的）执行完
//            或者等超时时间到
//            或者线程被中断，抛出InterruptedException
//            然后返回true（shutdown请求后所有任务执行完毕）或false（已超时）
            executor.awaitTermination(10, TimeUnit.SECONDS);
            map.forEach(Long.MAX_VALUE, (k, v) -> {
                if (v >= TARGET_TIMES) {
                    System.out.println(k + " occurs " + v + " times");
                }
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("programme run time:" + (endTime - startTime) + "ms");
    }
}
