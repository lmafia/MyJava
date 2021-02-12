package collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author L_MaFia
 * @classname SetTest.java
 * @description TODO
 * @date 2021/2/7
 */
public class SetTest {
    public static void main(String[] args) {
        HashSet<String> words = new HashSet<String>(16, (float) 0.75);
        long totalTime = 0;
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNext()) {
                String word = in.next();
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iterator = words.iterator();
        for (int i = 1; i <= 20 && iterator.hasNext(); i++) {
            System.out.println(iterator.next());
        }

        System.out.println("...");
        System.out.println(words.size() + " distinct words. " + totalTime + "ms");
    }
}
