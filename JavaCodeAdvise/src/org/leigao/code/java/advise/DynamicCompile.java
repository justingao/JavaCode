package org.leigao.code.java.advise;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class DynamicCompile
{
    /**
     * 动态编译一直是 Java 的梦想，从 Java 6 版本开始支持。
     * 可以在运行期直接编译 .java 文件，执行 .class，并能获得相关的输入输出，甚至还能监听相关事件。
     * 还可以直接编译、执行一段代码（也就是所谓的“空中编译”(on-the-fly)）。
     * 
     * 只要是能够在本地静态编译能够实现的任务，比如编译参数、输入输出、错误监控等，动态编译都能实现。
     * 
     * Java的动态编译对源提供了多个渠道，比如：可以是字符串（本文的例子），可以是文本文件，也可以是
     * 编译过的字节码 .class 文件，总之，只要是符合 Java 规范的就可以在运行期间动态加载，其实现方法
     * 就是实现 JavaFileObject 接口，重写 getCharContent、openInputStream、openOutStream，或者实现
     * JDK已经提供的两个 SimpleJavaFileObject、ForwardingJavaFileObject。
     * @throws Exception
     */
    public static void dynamicSourceCompile() throws Exception
    {
        // Java 源代码； 
        String javaSrc = "public class Hello{ public String sayHello(String name){return \"Hello, \" + name + \"!\";} }"; 
        
        // 类名及文件名； 
        String className = "Hello"; 
        
        // 方法名； 
        String methodName = "sayHello"; 
        
        // 当前编译器； 
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
        
        /**
         * TODO: 这里抛了异常，要继续研究下。
         * Exception in thread "main" java.lang.NullPointerException
    at org.leigao.code.java.advise.DynamicCompile.dynamicSourceCompile(DynamicCompile.java:34)
    at org.leigao.code.java.advise.DynamicCompile.main(DynamicCompile.java:72)
         */
        // Java 标准文件管理器； 
        StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null); 
        
        // Java 文件对象； 
        JavaFileObject fileObj = new StringJavaObject(className, javaSrc); 
        
        // 编译参数，类似于： javac <options> 中的 options；
        List<String> optsList = new ArrayList<String>(); 
        
        // 编译文件存放的地方，注意：此处是为 Eclipse 工具特别设置的； 
        optsList.addAll(Arrays.asList("-d", "./bin/")); 
        
        // 要编译的单元； 
        List<JavaFileObject> fileObjList = Arrays.asList(fileObj); 
        
        // 设置编译环境； 
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileMgr, null, optsList, null, fileObjList); 
        
        // 编译； 
        if(task.call())
        {
            // 生成对象； 
            Object obj = Class.forName(className).newInstance(); 
            Class<?> cl = obj.getClass(); 
            
            // 调用 sayHello() 方法； 
            Method method = cl.getMethod(methodName, String.class); 
            String str = (String)method.invoke(obj, "Dynamic Compilation"); 
            System.out.println(str);
        }
        
    }

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception
    {
        dynamicSourceCompile(); 
    }

}



class StringJavaObject extends SimpleJavaFileObject
{
    // 源代码； 
    private String content = ""; 

    /**
     * 遵循 Java 规范的类名及文件。 
     * @param _javaFilename
     * @param _content
     */
    protected StringJavaObject(String _javaFilename, String _content)
    {
        super(_createStringJavaObjectUri(_javaFilename), Kind.SOURCE);
        content = _content; 
    }
    
    
    /**
     * 产生一个 URI 资源路径； 
     * @param name
     * @return
     */
    private static URI _createStringJavaObjectUri(String name)
    {
        // 注意此处没有设置包名； 
        return URI.create("String:///" + name + Kind.SOURCE.extension); 
    }
    
    /**
     * 文本文件代码； 
     */
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors)
            throws IOException
    {
        return content; 
    }
}
