import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		int a[] = new int[n+1];
		for (int i = 1; i <= n; ++i) {
			a[i] = scan.nextInt();
		}
		solution(a, n);
 	}
	
	public static void solution(int a[], int n) {
		int res = 0;
		for (int i = n; i > 0; --i) {

			res += a[i];
			if (i % 2 == 0) {
				a[i / 2] = Math.max(a[i / 2] - a[i], 0);
			}
			else {
				a[i-1] = Math.max(a[i - 1] - a[i], 0);
				a[(i - 1) / 2] = Math.max(a[(i - 1) / 2] - a[i], 0);
			}
			a[i] = 0;
		}
		System.out.println(res);
	}
}