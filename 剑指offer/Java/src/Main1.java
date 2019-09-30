import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		String s[] = new String[n];
		scan.nextLine();
		for (int i = 0; i < n; ++i) {
			s[i] = scan.nextLine();
		}
		int a[] = new int[m];
		for (int i = 0; i < m; ++i)
			a[i] = scan.nextInt();
		solution(s, a, m, n);
	}
	
	public static void solution(String[] s, int[] a, int m , int n) {
		int res = 0;
		for (int i = 0; i < m; ++i) {
			int count = 0;
			int dict[] = new int[5];
			for (int j = 0; j < n; ++j) {
				int k = s[j].charAt(i) - 'A';
				dict[k] += a[i];
				count = Math.max(dict[k], count);
			}
			res += count;
		}
		System.out.println(res);
	}
}