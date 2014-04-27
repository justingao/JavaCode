package org.leigao.code.java.advise;

/*
 * 这里使用了静态导入。好处是：可以在代码中避免出现大量的 Math.PI 这样的代码，不用指定类名装饰。
 * 但是这同样也是坏处，阅读代码时很难搞清楚属性和方法的含义。
 * 
 * 因此对于静态导入，一定要遵循如下规则：
 * 1. 不要使用 * 通配符，除非是导入只包含静态常量的类或者接口；
 * 2. 方法名是具有明确清晰表象含义的工具类。 
 * 
 * 何谓“具有明确清晰表象含义的工具类”，比如 JUnit 中的 Assert 类中的 assertXXX() 方法。 
 */
import static java.lang.Math.*;     // 不好的例子； 

import org.junit.Test;
import static org.junit.Assert.*;   // 好例子。 

class StaticImport
{
    /**
     * 计算圆形的面积。 
     * @param r 半径
     * @return
     */
    public static double circleArea(double r)
    {
        return PI * r * r; 
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        double area = circleArea(1.0); 
        System.out.println("Circle Area: " + area);
    }
}


class StaticImportTest
{
    @Test
    public void testFoo()
    {
        assertEquals("foo", "foo"); 
        assertFalse(false); 
    }
}