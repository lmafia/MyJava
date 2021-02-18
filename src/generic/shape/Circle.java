package generic.shape;

/**
 * @Classname Circle
 * @Description TODO
 * @Date 2021/2/19 0:50
 * @Created by Administrator
 */
public class Circle extends Shape {


  @Override
  public void draw(Canvas canvas) {
    System.out.println("画上一个圆圈在" + canvas + "上");
  }
}
