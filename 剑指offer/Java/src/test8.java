/**
 * 二进制中1的个数
 * **/

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class test8 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		scan.nextLine();
		Set<Integer> countSet = new HashSet<>();
		for (int i = 0; i < T; ++i) {
			int countOf1 = 0;
			int n = scan.nextInt();
			scan.nextLine();
			for (int j = 0; j < n; ++j) {
				int number = scan.nextInt();
				while(number != 0) {
					++countOf1;
					number &= number - 1;
				}
				if (!countSet.contains(countOf1))
					countSet.add(countOf1);
				countOf1 = 0;
			}			
			System.out.println(countSet.size());
			scan.nextLine();
			countSet.clear();
		}
	}
}
