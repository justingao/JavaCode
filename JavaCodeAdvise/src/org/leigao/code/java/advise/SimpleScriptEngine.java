package org.leigao.code.java.advise;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;




/**
 * 脚本语言具备一些特质：
 * 1. 灵活。
 *      脚本语言一般都是动态类型，可以不用声明变量类型而直接使用，也可以在运行期间改变类型。
 * 2. 便捷。
 *      脚本语言是解释型语言，不需要编译成二进制代码，其执行依赖于解析器，因此在运行期更改
 *      代码非常容易，而且不用停止应用。
 * 3. 简单。
 *      技术门槛低。
 *      
 * Java 6 开始支持脚本语言，但是脚本语言非常多，Java 开发者也不知道应该支持哪一种或者哪几种，
 * 于是 JCP(Java Community Process) 提出了 JSR223 规范，只要符合该规范的语言都可以在 Java 平台
 * 上运行（它对 JavaScript 是默认支持的）。
 * @author Justin Lei GAO
 *
 */
public class SimpleScriptEngine
{
    /**
     * 这段代码演示了，通过键盘输入两个数值，然后通过 calc.js 脚本计算其运算结果。
     * 运行过程中，可以随时编辑 calc.js 脚本，实时改变其计算结果。而不用停止此应用程序。
     */
    public static void execShellScript() 
    {
        // 获得一个 JavaScript 的执行引擎； 
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript"); 
        
        // 建立上下文变量； factor 在 calc.js 中会用到。
        Bindings bind = engine.createBindings(); 
        bind.put("factor", 1); 
        
        // 绑定上下文，作用域是当前的引擎范围； 
        engine.setBindings(bind, ScriptContext.ENGINE_SCOPE); 
        
        Scanner input = null; 
        try
        {
            input = new Scanner(System.in); 
            while(input.hasNext())
            {
                int first = input.nextInt(); 
                int second = input.nextInt(); 
                System.out.println("Input is: " + first + ", " + second);
                
                // 执行 JavaScript 代码； 
                engine.eval(new FileReader(InnerUtils.getResourcePathByName(SimpleScriptEngine.class, "/calc.js"))); 
                
                // 是否可调用方法；
                if(engine instanceof Invocable)
                {
                    Invocable invoce = (Invocable)engine; 
                    // 执行 JavaScript 中的方法。  执行 calc.js 中的 calc() 方法进行计算
                    Double result = (Double)invoce.invokeFunction("calc", first, second); 
                    System.out.println("Result: " + result.intValue());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(null != input)
            {
                input.close(); 
            }
        }
    }
    

    /**
     * @param args
     * @throws ScriptException 
     * @throws NoSuchMethodException 
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) 
    {
        execShellScript(); 
    }

}
