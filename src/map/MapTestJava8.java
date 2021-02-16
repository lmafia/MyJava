package map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmafia
 */
public interface MapTestJava8 {

  public static void main(String[] args) {
    Map<String, Integer> map = new HashMap<String, Integer>(16);
    map.put("a", 109);
    map.put("b", 99);
    map.put("c", 89);

    map.replace("d", 66);
    System.out.println(map);
    map.merge("b", 10, (oldVal, param) -> (Integer)oldVal + (Integer)param);
    System.out.println(map);
    map.computeIfAbsent("d", (key) -> (4));
    System.out.println(map);
    map.computeIfPresent("d", (key, value) -> (Integer)value * (Integer)value);
    System.out.println(map);
    map.forEach((key, value) -> System.out.println(key + "-->" + value));
  }
}
