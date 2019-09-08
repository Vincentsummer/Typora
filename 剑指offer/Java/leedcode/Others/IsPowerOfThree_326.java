package Others;

public class IsPowerOfThree_326 {
	
	// 思路一：找出数字 n 是否是数字 b 的幂的一个简单方法是，n%3 只要余数为 0，就一直将 n 除以 b。最终结果是 1。
    public boolean isPowerOfThree(int n) {
        if (n < 1)
            return false;
        while (n % 3 == 0)
            n /= 3;

        return n == 1;
    }
    
    // 思路二：运算法
    public boolean isPowerOfThree1(int n) {
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
    }
    
    // 思路三：n 的类型是 int。在 Java 中说明了该变量是四个字节，最大值为 2147483647。
    // 由于 n 的限制，可以推断出 n 的最大值，也就是 3 的幂，是 1162261467。
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
