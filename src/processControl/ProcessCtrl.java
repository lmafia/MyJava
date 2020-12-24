package processControl;

/**
 * @author L_MaFia
 * @classname ProcessCtrl.java
 * @description TODO
 * @date 2020/12/6
 */
public class ProcessCtrl {
    public static void main(String[] args) {

        outer:
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 5) {
//                    continue outer;
                    break outer;
                }
//                System.out.println("j:" + j);
            }
            System.out.println("i: "+ i);
        }
    }

}
