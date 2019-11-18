package Greedy;

/**
 * 991. 坏了的计算器
 * 在显示着数字的坏计算器上，我们可以执行以下两种操作：
 * 双倍（Double）：将显示屏上的数字乘 2；
 * 递减（Decrement）：将显示屏上的数字减 1 。
 * 最初，计算器显示数字 X。返回显示数字 Y 所需的最小操作数。
 * **/

public class BrokenCalc_991 {
	
	//  贪心：用 Y 反推 X，存在两种操作，除 2 和 加 1。
	// 1. 当 Y 大于 X，能除以 2 时则除以 2，不能除以 2 则加 1 再除以 2
	// 2. 当 Y 小于 X，只执行加1。
    public int brokenCalc(int X, int Y) {
    	int result = 0;
    	while (Y > X) {
    		if (Y % 2 != 0) {
    			result += 2;
    			Y = (Y + 1) / 2;
    		}
    		else {
    			Y /= 2;
    			result++;
    		}
    	}
    	if (Y == X)
    		return result;
    	return result + (X - Y);
    }
}
