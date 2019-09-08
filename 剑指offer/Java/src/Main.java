
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int h = scan.nextInt();
		int k = scan.nextInt();
		
		int A[] = new int[n];
		for (int i = 0; i < n; ++i) {
			scan.nextLine();
			A[i] = scan.nextInt();
		}
		getSecond(A, n, h, k);
	}
	public static void getSecond(int A[], int n, int h, int k) {
		int res = 0, i = 1, count = A[0];
		while(i < n) {
			if (count + A[i] <= h) {
				count += A[i++];
				continue;
			}
			count = count - k < 0 ? 0 : count - k;
			res++;
		}
		if (count > 0) res += (count / k + 1);
		System.out.println(res);
	}
}