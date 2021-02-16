package collection.stream;

import java.util.stream.IntStream;

/**
 * @author lmafia
 * 使用stream操作集合
 * 1.使用Stream或XStream的builder类方法创建对应String的对应Builder
 * 2.重复调用Builder的add方法向Stream添加多个元素
 * 3.调用Builder的build方法获取对应的Stream
 * 4.调用Stream的聚集方法
 */
public class StreamTest {

  public static void main(String[] args) {
    IntStream is = IntStream.builder()
        .add(20)
        .add(21)
        .add(22)
        .add(23)
        .build();
//    System.out.println("The biggest in all elements: " + is.max().getAsInt());
//    下面的Stream操作只能执行一行，其他都要注释
//    System.out.println("The smallest in all elements: " + is.min().getAsInt());
//    System.out.println("The count: " + is.count());
//    System.out.println("The average: " + is.average());
//    System.out.println(
//        "Is the square of all the elements greater than 20: " + is.allMatch(ele -> ele * ele > 20));
//    System.out.println(
//        "Is the square of any the elements greater than 20: " + is.anyMatch(ele -> ele * ele > 20));
//    IntStream newIs = is.map(ele -> ele * 2);
//    newIs.forEach(System.out::println);
//    System.out.println(
//        "Is the square of any the elements greater than 20: " + is.filter(ele -> ele * ele > 20));
//    is.filter(ele ->  ele > 20).forEach(System.out::println);
    System.out.println(is.filter(ele -> ele > 20).count());
  }
}
