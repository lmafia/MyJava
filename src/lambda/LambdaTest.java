package lambda;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author L_MaFia
 * @classname LambdaTest.java
 * @description lambda 表达式 练习
 * @date 2021/1/25
 */
public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune"};
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted in dictionary order");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));
        System.out.println("Sorted by length");
        Arrays.sort(planets,
                (first, second) -> first.length() - second.length()

//                new Comparator<String>() {
//                    @Override
//                    public int compare(String o1, String o2) {
//                        return o1.length() - o2.length();
//                    }
//                }

        );
        System.out.println(Arrays.toString(planets));

        Timer timer = new Timer(1000, event -> System.out.println("The time is" + new Date()));
        timer.start();
        JOptionPane.showMessageDialog(null, "Quit programme?");
        System.exit(0);

    }
}
