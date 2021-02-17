package generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmafia
 */
public class GenericList {

  public static void main(String[] args) {
//    List strList = new ArrayList();
//    strList.add("book");
//    strList.add("email");
//    strList.add(5);

//    引入泛型
    List<String> strList = new ArrayList<String>();
    strList.add("book");
    strList.add("email");
//    下面代码会引起编译错误
//    strList.add(5);

    strList.forEach(str -> System.out.println(((String)str).length()));
  }

}
