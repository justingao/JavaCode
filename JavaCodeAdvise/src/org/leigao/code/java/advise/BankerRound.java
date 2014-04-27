package org.leigao.code.java.advise;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 四舍五入
 * 
 * 以银行存款为例，按照四舍五入算法：
 * 四舍：0.000 / 0.001 / 0.002 / 0.003 / 0.004 小于五的都可以舍去，银行不用付钱给储户
 *      银行可以赚到：0.000 + 0.001 + 0.002 + 0.003 + 0.004 = 0.010 
 * 五入：0.005 / 0.006 / 0.007 / 0.008 / 0.009 大于五的，需要进位，银行需要多付给储户：
 *       0.005 + 0.004 + 0.003 + 0.002 + 0.001 = 0.015
 * 按照 0.000 ~ 0.009 之间是等概率还计算，银行如果四舍五入就会亏损 0.005。
 * 
 * 这个算法误差是由美国银行家发现的，并且提出了一个修正算法（名字叫：银行家舍入 Banker's Round），规则如下：
 * 舍去位的数值小于5时，直接舍去；
 * 舍去位的数值大于等于6时，进位后舍去；
 * 舍去位等于5时，分两种情况：5后面还有其他数字（非0），则进位后舍去；5后面是0（5是最后一个数字），则根据5前
 * 一位的奇偶性来判断是否要进位，奇数进位，偶数舍去。
 * 
 * 银行家舍入汇总为一句话就是：
 * 四舍六入五考虑，五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一。
 * 
 * Java 支持七种舍入方式（RoundingMode 类）：
 * 1. ROUND_UP
 *      远离零方向舍入，也就是向绝对值最大的方向舍入，只要舍弃位非0就进位。
 * 2. ROUND_DOWN
 *      趋向零方向舍入，也就是向绝对值最小的方向舍入，所有舍弃位都丢弃，不存在进位的情况。
 * 3. ROUND_CEILING
 *      向正无穷方向舍入，如果是正数，其行为类似于 ROUND_UP；如果是负数，其行为类似于 ROUND_DOWN。
 *      Math.round() 采用的就是这个模式。
 * 4. ROUND_FLOOR
 *      向负无穷方向舍入，如果是正数，其行为类似于 ROUND_DOWN；如果是负数，其行为类似于 ROUND_UP。
 * 5. HALF_UP
 *      最近数字舍入（5进），这就是我们最经典的四舍五入模式。
 * 6. HALF_DOWN
 *      最近数字舍入（5舍），在四舍五入中，五是进位的，在这个模式下五是被舍弃掉的。
 * 7. HALF_EVEN
 *      银行家算法。
 * @author Justin Lei GAO
 *
 */
public class BankerRound
{
    private double v1 = 10.5; 
    private double v2 = -10.5; 
    
    /**
     * Math.round() 方法计算四舍五入。 
     * 
     * 可以看到，结果是：
     * Math.round(10.5) = 11
     * Math.round(-10.5) = -10
     */
    public void mathRound()
    {
        System.out.println("Math.round(" + v1 + ") = " + Math.round(v1));
        System.out.println("Math.round(" + v2 + ") = " + Math.round(v2));
    }
    
    /**
     * 银行家算法。
     */
    public void bankerRound()
    {
        // 本金
        BigDecimal d = new BigDecimal(88888888); 
        
        // 月利率，乘 3 计算季利率
        BigDecimal r = new BigDecimal(0.001875 * 3); 
        
        // 计算利息
        BigDecimal i = d.multiply(r).setScale(2, RoundingMode.HALF_EVEN); 
        
        System.out.println("i = " + i);
    }

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        BankerRound round = new BankerRound(); 
        round.mathRound(); 
        round.bankerRound(); 
    }

}
