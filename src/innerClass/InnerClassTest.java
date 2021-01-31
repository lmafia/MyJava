package innerClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author L_MaFia
 * @classname InnerClassTest.java
 * @description TODO
 * @date 2021/1/31
 */
public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock talkingClock = new TalkingClock(1000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "quit?");
        System.exit(0);
    }





}

class TalkingClock{
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep){
        this.interval = interval;
        this.beep = beep;
    }
    public void start(){
        /*
         *  局部内部类
         */
        class TimePrinter implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("the time is " + System.currentTimeMillis()+ "ms");
//           内部类可以访问 外部的beep
                if(beep){
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        /*
         *  匿名内部类
         */
        ActionListener actionListener = new ActionListener(){
                @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("the time is " + System.currentTimeMillis()+ "ms");
//           内部类可以访问 外部的beep
                if(beep){
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        TimePrinter timePrinter = new TimePrinter();
        Timer timer = new Timer(interval, actionListener);
        timer.start();
    }
    /*
     *  内部类
     */
//    public class TimePrinter implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("the time is " + System.currentTimeMillis()+ "ms");
////           内部类可以访问 外部的beep
//            if(beep){
//                Toolkit.getDefaultToolkit().beep();
//            }
//        }
//    }
}
