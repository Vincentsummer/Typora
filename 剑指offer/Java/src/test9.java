/***
 * 最长N串
 * */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test9 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		scan.nextLine();
		String[] str = new String[T];
		for (int i = 0; i < T; ++i) 
			str[i] = scan.nextLine();
		for (int i = 0; i < T; ++i) {
			int result = maxLengthOfNStr(str[i]);
			System.out.println(result);
		}
	}
	
	public static int maxLengthOfNStr(String str) {
		List<Integer> nonNIndex = new ArrayList<>();
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) != 'N')
				nonNIndex.add(i);
		}	
		int max = 0, n = nonNIndex.size();
		if (n <= 2)
			return str.length();
		for (int i = 0; i < n; ++i) {
			int num = nonNIndex.get(i);
			int tmp = 0;
			if (i < n - 2) {
				if (i == 0)
					tmp = nonNIndex.get(i+2) - 1;
				else 
					tmp = nonNIndex.get(i+2) - nonNIndex.get(i-1) - 1;
			}
			else if (i == n - 2)
				tmp = nonNIndex.get(n - 1) - nonNIndex.get(i-1) - 1;
			
			max = max > tmp ? max : tmp;
		}
		
		return max;
	}
}
