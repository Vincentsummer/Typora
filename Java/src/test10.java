import java.util.Scanner;

/**
 * 智慧老人的试炼
 * **/

public class test10 {
	private static int res;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		int A[] = new int[n];
		for (int i = 0; i < n; ++i)
			A[i] = scan.nextInt();
		res = Integer.MAX_VALUE;
		backtrack(A, 0,  n-1, 0);
		System.out.println(res);
	}
	
	public static void backtrack(int A[], int left, int right, int count) {
		if (left > right) {
			res = Math.min(res, count);
			return;
		}
		if (A[left] > A[right]) count += A[left++];
		else count += A[right--];
		if (count >= res) return;
		backtrack(A, left+1, right, count);
		backtrack(A, left, right-1, count);
	}
}
