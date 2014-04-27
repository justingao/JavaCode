package org.leigao.code.java.advise;

/**
 * Java 中有四种类型的代码块（Code Block）：
 * 1. 普通代码块。
 *      就是普通方法后面用 {} 括起来的代码片段，不能单独执行，不许通过方法名称调用。
 * 2. 静态代码块。
 *      在类中使用static修饰，并使用{}括起来的代码片段，用于静态变量初始化或对象创建前的环境初始化。
 * 3. 同步代码块。
 *      使用synchronized修饰，并使用{}括起来的代码片段，表示同一时间只能有一个线程进入到该方法中。
 * 4. 构造代码块。
 *      在类中没有任何前缀和后缀，并使用{}括起来的代码片段。
 * 
 * 
 * @author Justin Lei GAO
 *
 */
public class CodeBlock
{
    private String name; 
    
    public void foo()
    {
        System.out.println(name);
    }

    
    public CodeBlock()
    {
        System.out.println("无参构造函数");
    }
    
    public CodeBlock(String name)
    {
        System.out.println("执行有参构造函数");
        this.name = name; 
    }

    /**
     * 这段代码就是构造代码块。
     * 其位置没有特殊要求。
     * 
     * 一般会被构造代码块用在如下场景中：
     * 1. 初始化实例变量
     *      如果每个构造函数都要初始化变量，可以通过构造代码块来实现。
     *      当然也可以通过定义一个方法，在每个构造函数中调用的方式来实现；采用构造代码块，
     *      编译器会直接将其写入到每个构造函数中，更佳。
     * 2. 初始化实例环境
     *      本对象依赖的其他对象可以在构造代码块中检查并创建。
     *      
     * 构造代码块有两个特征：在每个构造函数中都会运行，以及在构造函数中它会首先被运行。
     */
    {
        System.out.println("执行构造代码块");
    }
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        CodeBlock cb = new CodeBlock(); 
        cb.foo(); 
        
        SmartCodeBlock b1 = new SmartCodeBlock(); 
        SmartCodeBlock b2 = new SmartCodeBlock("xx"); 
        SmartCodeBlock b3 = new SmartCodeBlock(333); 
        b1.getObjectCount(); 
    }

}



class SmartCodeBlock
{
    // 对象计数器； 
    private static int count = 0; 
    
    /**
     * 这段代码用于在创建本对象前计数对象的数量。 
     */
    {
        count++; 
    }
    
    public SmartCodeBlock()
    {
        ;
    }
    
    /**
     * 虽然在此构造方法中有调用了另外一个构造方法，但是构造代码块并不会多计数。
     * Java编译器智能的对 this() / super() 方法进行了特殊处理，这样确保了每个
     * 构造代码块在每个构造方法中只执行一次。
     * @param name
     */
    public SmartCodeBlock(String name)
    {
        this(); 
    }
    
    public SmartCodeBlock(int num)
    {
        ;
    }
    
    public void getObjectCount()
    {
        System.out.println("总共有：" + count + "个对象。");
    }
}
