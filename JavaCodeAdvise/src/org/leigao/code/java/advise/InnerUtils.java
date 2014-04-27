package org.leigao.code.java.advise;

/**
 * 内部类，用于辅助其他类的公共操作。 
 * @author Justin Lei GAO
 *
 */
public class InnerUtils
{
    public static final String RESOURCES = "resources"; 
    
    /**
     * 获取某个类的资源文件路径。
     * @param c
     * @param filename
     * @return
     */
    public static String getResourcePathByName(@SuppressWarnings("rawtypes") Class c, String filename)
    {
        return RESOURCES + "/" + c.getSimpleName() + "/" + filename; 
    }
}
