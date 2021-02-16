package collection.treeSet;

import java.util.Comparator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * @author L_MaFia
 * @classname TreeSetTest.java
 * @description TODO
 * @date 2021/2/7
 */
public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet<Item> items = new TreeSet<>();
        items.add(new Item("Toaster", 1234));
        items.add(new Item("Widget", 2345));
        items.add(new Item("Modem", 3456));
        System.out.println("" + items);

//        用下面的Comparator的方式，不会使用Item的compareTo来比较
        TreeSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortByDescription.add(new Item("Toaster", 1234));
        sortByDescription.add(new Item("Widget", 2345));
        sortByDescription.add(new Item("Modem", 3456));
        System.out.println("" + sortByDescription);

    }
}


class Item implements Comparable<Item> {
    private String description;
    private int partNumber;

    public String getDescription() {
        return description;
    }
    public int getPartNumber() {
        return partNumber;
    }
    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    @Override
    public String toString() {
        return "Item{" +
                "description='" + description + '\'' +
                ", partNumber=" + partNumber +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item other = (Item) o;
        return Objects.equals(description, other.description) && partNumber == other.partNumber;
    }

    @Override
    public int compareTo(Item o) {
        int diff = Integer.compare(partNumber, o.partNumber);
        return diff != 0 ? diff : description.compareTo(o.description);
//        return description.compareTo(o.description);
    }
//    @Override
//    public int hashCode(){
//        return Objects.hash(description, partNumber);
//    }

}