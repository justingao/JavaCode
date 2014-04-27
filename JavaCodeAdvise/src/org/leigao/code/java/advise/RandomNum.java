package org.leigao.code.java.advise;

import java.util.Random;

/**
 * 随机数：除非有必要，否则不要设置随机数种子。
 * 
 * 在 Java 中有两种方法可以获得不同的随机数：
 * 通过 java.util.Random 类获得随机数的原理和 Math.random 方法相同，Math.random() 方法
 * 也是通过生成一个 Random 类的实例，然后委托 nextDouble() 方法的。两者殊途同归，没差别。
 * @author Justin Lei GAO
 *
 */
public class RandomNum
{
    /**
     * Random 类的默认种子，是 System.nanoTime() 的返回值，这个值是距离某个固定时间点
     * 的纳秒数，不同的操作系统和硬件有不同的固定时间点。
     * 
     * 所以每次运行时，种子的值也在变化。
     */
    public static void random()
    {
        Random r = new Random(); 
        for(int i = 0; i < 4; i++)
        {
            System.out.println(r.nextInt());
        }
    }
    
    /**
     * 指定了固定的种子之后，程序每次运行产生的结果都一样。因为 Java 中，随机数的产生取决
     * 于种子，随机数和种子时间的关系：
     * 1. 种子不同，产生不同的随机数；
     * 2. 种子相同，即使实例不同也产生相同的随机数。
     * 
     * 所以下面这段代码，无法程序运行几次，输出的随机数都是相同的序列。
     */
    public static void randomSeed()
    {
        Random r = new Random(1000); 
        for(int i = 0; i < 4; i++)
        {
            System.out.println(r.nextInt());
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.println("Random without specified seed: ");
        random(); 
        
        System.out.println("\nRandom with specified seed: ");
        randomSeed(); 
    }

}
