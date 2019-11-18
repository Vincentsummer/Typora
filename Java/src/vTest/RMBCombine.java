package vTest;

import java.util.Scanner;

/**
 * 给出1元，5元，10元，20元，50元、100元六种货币若干，如果要买一件商品x元，有多少种货币组成方式？
 * 求各种组成方式的货币数量总和。  
 * **/

public class RMBCombine {

	public static void main(String[] args) {
		int money[] = {1, 5, 10, 20, 50, 100};
		int n = money.length;
		int count[] = new int[n];
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < n; ++i)
			count[i] = scan.nextInt();
		scan.nextLine();
		int number =  scan.nextInt();
		int sum = 0, k = 0;
		int result = rmbCombine(money, count, number, n-1, k, sum);
		System.out.println(result);
	}
	
	public static int rmbCombine(int[] money, int[] count, int number, int n, int k, int sum) {

		if (n < 0)
			return -k;
		for (int i = 0; i <= count[n]; ++i) {
			int num = number - i * money[n];
			if (num == 0) {	
				return i;
			}
			if (num < 0)
				return  0;
			
			int tmp = rmbCombine(money, count, num , n-1, i, sum);
			if (tmp == 0)
				tmp = -k;
			else
				sum += k + tmp;
			System.out.println(sum);
		}
		return sum;
	}
}
