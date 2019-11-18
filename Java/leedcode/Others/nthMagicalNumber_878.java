package Others;

public class nthMagicalNumber_878 {
	/**
	 * 思路：
	 * 神奇数字的产生：A的倍数，B的倍数，A和B的公倍数。
	 * 那么根据容斥原理，给定一个数N,那么小于等于N的数字中，神奇数字的个数应该为N/A+N/B-N/AB的最小公倍数。
	 * 而最小公倍数等于A*B/AB的最大公约数；
	 * 建立一个大区间，通过二分法来求解，大区间的mid，可以知道mid左边有mid/A个A，mid/B个B ，mid/G个AB的
	 * 最小公倍数，mid/A+mid/B-mid/g就代表mid左边有多少个满足条件的数。然后二分法求解，直到找到一定个数的
	 * 满足条件的数。
	 * **/
    public int nthMagicalNumber(int N, int A, int B) {
    	long low = 0, high = 2000000000000000000L;
    	int g = A * B / gcd(A, B);
    	while (low < high) {
    		long mid = (low + high) / 2;
    		long t  = mid / A + mid / B - mid / g;
    		if (t < N)
    			low = mid + 1;
    		else
    			high = mid;
    	}
    	return (int)(high % 1000000007);
    }
    
    // 求最大公约数
    public int gcd(int a, int b) {
    	return b == 0 ? a : gcd(b, a%b);
    }
}
