/**举重大赛**/

package vTest;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int person[] = new int[N];
		scan.nextLine();
		for (int i = 0; i < N; ++i) {
			person[i] = scan.nextInt();
		}
		
		int result = maxCount(person, N);
		System.out.println(result);
		
	}
	
	public static int maxCount(int person[], int N) {
		int result = 0;
		for (int i = 0; i < N; ++i) {
			for (int j = i; j < N; ++j) {
				if (i == j)
					continue;
				else if (person[i] >= person[j] * 0.9)
					result ++;
			}
		}
		return result;
	}
}
