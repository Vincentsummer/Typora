import java.util.Scanner;

public class Main2 {
	private static int MOD = 1_009;
	public static void main(String[] args) { 
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int A[][] = new int[n][m];
		int B[][] = new int[n][m];
		for (int i = 0 ; i < n; ++i) {
			scan.nextLine();
			for (int j = 0; j < m; ++j)
				A[i][j] = scan.nextInt();
		}
		for (int i = 0 ; i < n; ++i) {
			scan.nextLine();
			for (int j = 0; j < m; ++j)
				B[i][j] = scan.nextInt();
		}
		matrixMultiply(A, B, n, m);
	}
	
	public static void matrixMultiply(int[][] A, int[][] B, int n, int m) {
		int tmp[][] = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int c = 0;
				for (int k = 0; k < m; ++k) {
					c += B[i][k] * B[j][k];
					c %= MOD;
				}
				tmp[i][j] = c;
			}
		}
		int res[][] = new int[n][m];
		System.out.println(n + " " + m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; ++j) {
				int c = 0;
				for (int k = 0; k < n; ++k) {
					c += tmp[i][k] * A[k][j];
					c %= MOD;
				}
				res[i][j] = c;
				System.out.print(c + " ");
			}
			System.out.println("");
		}
	}
}