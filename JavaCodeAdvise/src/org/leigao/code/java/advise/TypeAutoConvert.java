package org.leigao.code.java.advise;

/**
 * Java 中，是先计算然后再进行类型转换的。
 * 在本类中， dist 的两个参数都是 int 型的，所以其运算结果也是 int 型的（当然已经超出了
 * int的最大值，所以变成了负值），然后再将这个负值的 int 型转换为 long 型，肯定也还是负值。
 * 
 * 要解决这个问题，像 dist2 一样，在参与运算的数值中指定为 long 型即可。
 * @author Justin Lei GAO
 *
 */
public class TypeAutoConvert
{
    // 光速：30万公里/秒。 
    private static final int LIGHT_SPEED = 30 * 10000 * 1000; 

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("LIGHT_SPEED        = " + LIGHT_SPEED);
        
        /**
         * 这里的输出是：
         * LIGHT_SPEED * 10   = -1294967296
         */
        long dist = LIGHT_SPEED * 10; 
        System.out.println("LIGHT_SPEED * 10   = " + dist);
        
        /**
         * 这里的输出是：
         * LIGHT_SPEED * 10   = 3000000000
         */
        long dist2 = LIGHT_SPEED * 10L; 
        System.out.println("LIGHT_SPEED * 10   = " + dist2);
    }

}
