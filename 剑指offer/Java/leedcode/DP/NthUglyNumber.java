package DP;

/**
 * 264. 丑数2
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。（1,2,3,4,5,6,8,9,10,12，....）
 * **/

public class NthUglyNumber {
	
	// 基本解法，依次判断每个数是否为丑数
    public int nthUglyNumber(int n) {
    	if (n <= 0)
    		return 0;
    	int count = 0, number = 0; 
    	while (count < n) {
    		++number;
    		if (isUglyNumer(number))
    			++count;
    	}
    	return number;
    }
    
    public boolean isUglyNumer(int number) {
    	while (number % 2 == 0)
    		number /= 2;
    	while (number % 3 == 0)
    		number /= 3;
    	while (number % 5 == 0)
    		number /= 5;
    	
    	return number == 1 ? true : false;
    }
    
    // 思路二：三指针法
    public int nthUglyNumber2(int n) {
    	if (n <= 0)
    		return 0;
    	int uglyNumer[] = new int[n];
    	uglyNumer[0] = 1;
    	int p2 = 1, p3 = 1, p5 = 1;
    	for (int i = 1; i < n; ++i) {
    		int next = Math.min(uglyNumer[p2]*2, Math.min(uglyNumer[p3]*3, uglyNumer[p5]*5));
    		if (next == uglyNumer[p2]*2)
    			++p2;
    		if (next == uglyNumer[p3]*3)
    			++p3;
    		if (next == uglyNumer[p5]*5)
    			++p5;
    		uglyNumer[i] = next;
    	}
    	return uglyNumer[n-1];
    }
}
