package generic.shape;

import java.util.List;

/**
 * @Classname Canvas
 * @Description TODO
 * @Date 2021/2/19 0:53
 * @Created by Administrator
 */
public class Canvas {

//使用被限制的泛型通配符，Shape 是 通配符的上限（ supper ）
  public void drawAll(List<? extends Shape> shapes) {
    for (Shape shape : shapes) {
      shape.draw(this);
    }
  }

}
