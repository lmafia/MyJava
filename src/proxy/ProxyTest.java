package proxy;

import javax.sound.midi.Track;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author L_MaFia
 * @classname ProxyTest.java
 * @description TODO
 * @date 2021/1/31
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[100];
        for (int i = 0; i < elements.length; i++){
            Integer value = i + 1;
            TraceHandler traceHandler = new TraceHandler(value);
            Class[] interfaces = new Class[]{Comparable.class};
            Object proxy = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), interfaces, traceHandler);
            Class proxyClass = Proxy.getProxyClass(ClassLoader.getSystemClassLoader(), interfaces);
            elements[i] = proxy;
    }

        Integer key = new Random().nextInt(elements.length) + 1;
        int result = Arrays.binarySearch(elements, key);
        if (result >= 0) {
            System.out.println(elements[result]);
        }


    }


}

/**
 * 由于数组中填充了代理对象，所以compareTo调用了额TraceHandler的 invoke 方法，打印出了方法名和参数，然后在包装的Integer对象上调用了compareTo。
 */

class TraceHandler implements InvocationHandler{

    private Object target;
    public TraceHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(target);
        System.out.print("." + method.getName() + "(");

        if (args != null){
            for (int i = 0; i < args.length; i++){
                System.out.print(args[i]);
                if ( i < args.length - 1){
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
        return method.invoke(target, args);
    }
}

/*
* result：
50.compareTo(90)
75.compareTo(90)
88.compareTo(90)
94.compareTo(90)
91.compareTo(90)
89.compareTo(90)
90.compareTo(90)
90.toString()
90
* */
