package Others;
/**
 * 762. 二进制表示中质数个计算置位
 * **/

public class countPrimeSetBits_762 {
	// 暴力解
    public int countPrimeSetBits(int L, int R) {
    	int res = 0;
        for (int i = L; i <= R; ++i) {
        	int count = 0, tmp = i;
        	while(tmp != 0) {
        		count++;
        		tmp ^= (tmp-1);	
        	}
        	System.out.println(count);
        	if (isPrimeNumber(count))
        		res++;
        }
        return res;
    }
    
    public boolean isPrimeNumber(int n) {
    	for (int i = 2; i <= Math.sqrt(n); ++i) {
    		if (n % i == 0)
    			return false;
    	}
    	return true;
    }
    
    /**
     * L，R最大为10^6，转换为二进制，有20位，故计算置位个数不会超过20。即求出20以内的质数列表即可。
     * 使用 Integer.bitCount(i) 函数可快速求得i的二进制形式中1的个数。
     * **/
    public int countPrimeSetBits1(int L, int R) { 
    	//0-20的质数列表，prime[i]为1，则i为质数 
    	int[] primes = {0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1}; 
    	int res = 0; 
    	for (int i = L; i <= R; i++) { 
    		int t = Integer.bitCount(i); 
    		res += primes[t]; 
    	} 
    	return res; 
	}
}
