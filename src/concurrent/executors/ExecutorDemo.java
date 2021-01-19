package concurrent.executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author L_MaFia
 * @classname ExecutorDemo.java
 * @description 使用executor创建线程池，查询文件的关键词次数
 * @date 2021/1/19
 */
public class ExecutorDemo {

    /**
     * 查找文件中的关键词
     *
     * @param word
     * @param path
     * @return
     */
    public static long occurrences(String word, Path path) {
        try (Scanner in = new Scanner(path)) {
            int count = 0;
            while (in.hasNext()) {
                if (in.next().equals(word)) {
                    count++;
                    System.out.println(word + "出现在" + path + "中");
                }
            }
            return count;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 遍历文件生成文件集合
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static Set<Path> descendants(Path path) throws IOException {
        try (Stream<Path> entries = Files.walk(path)) {
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());
        }
    }

    /**
     * 查找出现关键词的的文件
     * @param word
     * @param path
     * @return
     */
    public static Callable<Path> searchForTask(String word, Path path) {
        return () -> {
            try (Scanner in = new Scanner(path)) {
                while (in.hasNext()) {
                    if (in.next().equals(word)) {
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled");
                        return null;
                    }
                }
            }
            //不希望一个任务失败就停止搜索了，所以失败的任务就要抛出一个异常
            throw new NoSuchElementException();
        };
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
       try (Scanner in = new Scanner(System.in)){
           System.out.println("请输入路径：");
           String start = in.nextLine();
           System.out.println("请输入关键词：");
           String word = in.nextLine();

           Set<Path> descendants = descendants(Paths.get(start));
           ArrayList<Callable<Long>> tasks = new ArrayList<Callable<Long>>();
           for(Path path : descendants){
               Callable<Long> task = ()->occurrences(word, path);
               tasks.add(task);
           }

           ExecutorService executor = Executors.newCachedThreadPool();
           Instant now = Instant.now();
           List<Future<Long>> results = executor.invokeAll(tasks);
           ExecutorCompletionService<Long> service = new ExecutorCompletionService<>(executor);
           for (Callable<Long>task : tasks){
               service.submit(task);
           }
           long total = 0L;
           for (int i = 0; i< tasks.size(); i++){
               total += service.take().get();
           }
//           System.out.println(word + "的出现次数为"+ total);
//           total = 0L;
//           for(Future<Long> result : results){
//               total += result.get();
//           }
           Instant end = Instant.now();
           System.out.println(word + "的出现次数为"+ total);
           System.out.println("花费的时间为：" + (Duration.between(now,end).toMillis() + "ms"));


           ArrayList<Callable<Path>> searchTasks = new ArrayList<>();
           for (Path path : descendants){
               searchTasks.add(searchForTask(word, path));
           }
           Path found = executor.invokeAny(searchTasks);
           System.out.println(word + "第一次出现在" + found);
           if (executor instanceof  ThreadPoolExecutor){
               System.out.println("线程池的最大核心数为" + ((ThreadPoolExecutor) executor).getLargestPoolSize());
           }
           executor.shutdown();

       }

    }
}
