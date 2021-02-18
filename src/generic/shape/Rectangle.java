package generic.shape;

/**
 * @Classname Rectangle
 * @Description TODO
 * @Date 2021/2/19 0:52
 * @Created by Administrator
 */
public class Rectangle extends Shape {


  @Override
  public void draw(Canvas canvas) {
    System.out.println("画一个矩形在" + canvas + "上");
  }
}
