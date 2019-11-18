import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		for (int i = 0; i < T; ++i) {
			scan.nextLine();
			int N = scan.nextInt();
			solution(N);
		}
	}
	
	public static void solution(int N) {
		int res = 0;
		for (int i = 1; i < N; i++) {
			if ((N - i) % (i + 1) == 0) {
				int j = (N - i) / (i + 1);
				if (j >= i) {
					res++;
				}
				else {
					break;
				}
			}
		}
		System.out.println(res);
	}
}