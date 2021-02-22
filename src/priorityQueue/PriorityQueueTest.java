package priorityQueue;

import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * @author L_MaFia
 * @classname PriorityQueueTest.java
 * @description TODO
 * @date 2021/2/7
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1934, 1, 31));
        pq.add(LocalDate.of(1821, 9,6));
        pq.add(LocalDate.now());
        System.out.println("Iterating over elements . . . ");
        for (LocalDate date : pq) {
            System.out.println(date);
        }
        System.out.println("Removing elements . . . ");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }

}
