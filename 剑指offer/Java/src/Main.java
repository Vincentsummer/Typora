import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int m = scan.nextInt();
		scan.nextLine();
		int a[] = new int[m];
		for (int i = 0; i < m; ++i) {
			a[i] = scan.nextInt();
		}
		solution(a, m);
 	}
	
	public static void solution(int a[], int m) {
		Arrays.sort(a);
		int res = 0, sum = 0;
		for (int i = 0; i < m; ++i) {
			sum += a[i];
		}
		int avg = sum / m, re = m - sum % m;
		int j = 0;
		while (j < m) {
			if (j < re && a[j] < avg) {
				res += avg - a[j];
			}
			else if (j >= re && a[j] < avg + 1) {
				res += avg - a[j] + 1;
			}
			j++;
		}
		System.out.println(res);
	}
}