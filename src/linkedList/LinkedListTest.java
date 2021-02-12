package linkedList;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * @author L_MaFia
 * @classname LinkedListTest.java
 * @description
 *
 * Iterator和ListIterator主要区别有：
 *
 * 一、ListIterator有add()方法，可以向List中添加对象，而Iterator不能。
 *
 * 二、ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历。但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator就不可以。
 *
 * 三、ListIterator可以定位当前的索引位置，nextIndex()和previousIndex()可以实现。Iterator 没有此功能。
 *
 * 四、都可实现删除对象，但是ListIterator可以实现对象的修改，collection.set()方法可以实现。Iterator仅能遍历，不能修改。因为ListIterator的这些功能，可以实现对LinkedList等List数据结构的操作。
 * @date 2021/2/7
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("Amy");
        list1.add("Carl");
        list1.add("Erica");

        LinkedList<String> list2 = new LinkedList<>();
        list2.add("Bob");
        list2.add("Doug");
        list2.add("Frances");
        list2.add("Gloria");

        ListIterator<String> list1Iterator = list1.listIterator();
        Iterator list2Iterator = list2.iterator();
        while (list2Iterator.hasNext()) {
            if (list1Iterator.hasNext()) {
                list1Iterator.next();
            }
//            ListIterator可以给list添加元素，但是iterator不行
            list1Iterator.add((String) list2Iterator.next());
        }

        System.out.println(list1);
//       result: [Amy, Bob, Carl, Doug, Erica, Frances, Gloria] 隔一个插入一个

//       重新创建迭代器，之前的迭代器已经指向最后面了
        list2Iterator = list2.iterator();
//        隔一个删除一个
        while (list2Iterator.hasNext()) {
            list2Iterator.next();
            if (list2Iterator.hasNext()) {
                list2Iterator.next();
//                iterator只能删除或遍历元素
                list2Iterator.remove();
            }
        }
        System.out.println(list2);

//        删除之前merge之后的list中list2（隔一个删除一个）的元素
        list1.removeAll(list2);
        System.out.println(list1);


    }
    
}
