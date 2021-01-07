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
 * @description TODO
 * @date 2021/1/8
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final Path DUMMY = Paths.get("");
    private static final BlockingQueue<Path> QUEUE = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/dir): ");
            String directory = scanner.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = scanner.nextLine();

            Runnable enumerator = () -> {
                try {
                    enumerate(Paths.get(directory));
                    QUEUE.put(DUMMY);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(enumerator).start();
            for (int i = 1; i  <= SEARCH_THREADS; i++){
                Runnable searcher = () -> {
                  try  {
                      boolean isDone = false;
                      while (!isDone){
                          Path file = QUEUE.take();
                          if (file == DUMMY) {
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

    private static void enumerate(Path directory) throws IOException, InterruptedException {
        try (Stream<Path> children = Files.list(directory)) {
            for (Path child : children.collect(Collectors.toList())) {
                if (Files.isDirectory(child)) {
                    enumerate(child);
                } else {
                    QUEUE.put(child);
                }
            }
        }
    }

    private static void search(Path file, String keyword) throws IOException {
        try (Scanner scanner = new Scanner(file, "UTF-8")){
            int lineNumber = 0;
            while (scanner.hasNextLine()){
                lineNumber++;
                String line = scanner.nextLine();
                if (line.contains(keyword)){
                    System.out.printf("%s:%d:%s%n", file, lineNumber, line);
                }
            }

        }
    }



}
