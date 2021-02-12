package clone;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author L_MaFia
 * @classname Employee.java
 * @description TODO
 * @date 2021/1/24
 */
public class Employee implements Cloneable {
    private String name;
    private double salary;

    public Employee(String name, double salary) throws CloneNotSupportedException {
        this.name = name;
        this.salary = salary;
        this.hireDate = new Date();
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
//        Date 需要深拷贝
        cloned.hireDate = (Date) hireDate.clone();
        return cloned;
    }

    private Date hireDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public void setHireDate(int year, int month, int day){
        GregorianCalendar newDate = new GregorianCalendar(year, month, day);
        hireDate.setTime(newDate.getTimeInMillis());
    }
    public void raiseSalary(double byPercent){
        double raise = this.salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDate=" + hireDate +
                '}';
    }
}
