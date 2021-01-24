package clone;

/**
 * @author L_MaFia
 * @classname CloneTest.java
 * @description  java - 深拷贝 - 浅拷贝 练习
 * @date 2021/1/24
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee johnQ = new Employee("John Q", 5000);
        johnQ.setHireDate(2000, 1, 1);
        Employee johnQ2 = johnQ.clone();
//        基础数据类型可以不用深拷贝
        johnQ2.raiseSalary(10);
//        Date 引用类的 需要深拷贝
        johnQ2.setHireDate(2021, 1, 1);
//        String 类 虽然也是 引用类， 但是字符串是常量，会指向不同字符串上
        johnQ2.setName("John Q2");
        System.out.println("origin: " + johnQ.toString());
        System.out.println("copy: "+ johnQ2.toString());
    }
}
