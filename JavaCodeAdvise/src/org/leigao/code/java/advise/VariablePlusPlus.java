package org.leigao.code.java.advise;

public class VariablePlusPlus
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        int count = 0; 
        for(int i = 0; i < 10; i++)
        {
            /*
             * count++ 表达式是否返回值的，其返回值就是 count 自加前的值，所以这段
             * 代码 count++ 之后返回的还是 0。
             * 最后输出结果是：count=0
             * 
             * 但是在 C++ 中，count++ 与 count = count++ 是等效的。
             */
            count = count++; 
        }
        System.out.println("count=" + count);
    }
}
