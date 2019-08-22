/**
 * 解码
 * **/

import java.util.Scanner;

public class test10 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int K = scan.nextInt();
		scan.nextLine();
		String str = scan.nextLine();
		
		enCode(N, K, str);	
	}

	public static  void enCode(int N, int K, String str) {
		int[] result = new int[N];
		for (int i = 0; i < N; ++i) {
			int c = (int)(str.charAt(i) - '0');
			if (i == 0)
				result[i] = c;
			else {
				int j = i - 1;
				result[i] = result[i] ^ c;
				while (j >= Math.max(0, i - K + 1)) {
					result[i] = result[i] ^ result[j];
					j--;
				}
			}
			System.out.print(result[i]);
		}
	}
}
