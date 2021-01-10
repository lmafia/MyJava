package subjects;

import java.util.Scanner;

/**
 * @author L_MaFia
 * @classname for1.java
 * @description 最小公倍数，最大公约数
 * @date 2021/1/10
 */
public class for1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入正整数1:");
        int a = in.nextInt();
        System.out.println("请输入正整数2:");
        int b = in.nextInt();

        int max = a >= b ? a : b;
        int min = a <= b ? a : b;
        int divisor = 1;
//        找出最大公约数
        for (int i = min; i >= 1; i--) {
            if (a % i == 0 && b % i == 0) {
                System.out.println(i);
                divisor = i;
//         找出最小公倍数
                System.out.println(a * b / divisor);
                break;
            }
        }

//       找出最小公倍数
        for (int i = max; i <= a * b; i += divisor) {
            if (i % a == 0 && i % b == 0) {
                System.out.println(i);
                break;
            }
        }

    }


}
