package thread.blockingQueue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author L_MaFia
 * @classname BlockingQueueTest.java
 * @description 用阻塞队列控制一组线程
 * 程序子一个目录及其子目录下搜索所有文件，打印出包含指定关键字的行与内容
 * @date 2021/1/8
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 10;
    /**
     * 使用空路径作为结束包
     */
    private static final Path DUMMY = Paths.get("");
    private static final BlockingQueue<Path> QUEUE = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/dir): ");
            String directory = scanner.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = scanner.nextLine();
            long startTime = System.currentTimeMillis();
            Runnable enumerator = () -> {
                try {
                    //通过递归调用把目录的文件都put到queue里
                    enumerate(Paths.get(directory));
                    //放完所有文件到队列里后,发送一个空包
                    QUEUE.put(DUMMY);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();
            for (int i = 1; i <= SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    try {
//                      初始化状态
                        boolean isDone = false;
                        while (!isDone) {
//                         取出一个文件
                            Path file = QUEUE.take();
                            if (file == DUMMY) {
//                            返回空包,设置完成状态
                                QUEUE.put(file);
                                isDone = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                        System.out.println(e);
                    }
                };
                new Thread(searcher).start();
            }
        }

    }

    /**
     * 枚举出目录下的文件，推到阻塞队列
     *
     * @param directory
     * @throws IOException
     * @throws InterruptedException
     */
    private static void enumerate(Path directory) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(directory)) {
            for (Path child : children.collect(Collectors.toList())) {
                //递归找出子目录的文件
                if (Files.isDirectory(child)) {
                    enumerate(child);
                } else {
                    QUEUE.put(child);
                }
            }
        }
    }

    /**
     * 读取文件，搜索关键词，输出相应内容
     *
     * @param file
     * @param keyword
     * @throws IOException
     */
    private static void search(Path file, String keyword) throws IOException {
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();
                if (line.contains(keyword)) {
                    System.out.printf("%s:%d:%s%n", file, lineNumber, line);
                }
            }

        }
    }


}
