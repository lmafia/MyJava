
package generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Classname GenericMethodTest
 * @Description TODO
 * @Date 2021/2/19 1:25
 * @Created by Administrator
 */
public class GenericMethodTest {
  static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
    for (T t : a) {
      c.add(t);
    }
  }

  public static void main(String[] args) {
    Object[] oa = new Object[100];
    Collection<Object> co = new ArrayList<>();
    fromArrayToCollection(oa, co);
    String[] sa = new String[100];
    Collection<String> cs = new ArrayList<>();
    fromArrayToCollection(sa,cs);
  }

}

