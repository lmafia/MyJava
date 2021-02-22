package subjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author L_MaFia
 * @classname Gobang.java
 * @description TODO
 * @date 2021/2/23
 */
public class Gobang {
    private static int BOARD_SIZE = 15;
    private String[][] board;

    public void initBoard() {
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "+";
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Gobang gb = new Gobang();
        gb.initBoard();
        gb.printBoard();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        while (true) {
            try {
                System.out.println("请输入您的坐标 (x,y): ");
                if ((input = bufferedReader.readLine()) == null) break;
                String[] posStrArr = input.split(",");
                int x = Integer.parseInt(posStrArr[0]);
                int y = Integer.parseInt(posStrArr[1]);
                gb.board[y - 1][x - 1] = "O";
                int x2 = (int) (Math.random() * 15);
                int y2 = (int) (Math.random() * 15);
                gb.board[y2 - 1][x2 - 1] = "X";
                gb.printBoard();
            } catch (Exception e) {
                System.out.println("输入的坐标不合法,请重新输入: ");
            }

        }
    }
}
