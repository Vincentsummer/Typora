import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	private static int k = 0;
	private static boolean flag = false;
	public static void main(String[] args) { 
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(), M = scan.nextInt();
		scan.nextLine();
		List<String> str = new ArrayList<>();
		int m[][] = new int[M][2];
		for (int i = 0; i < N; ++i)
			str.add(scan.nextLine());
		for (int i = 0; i < M; ++i) {
			m[i][0] = scan.nextInt();
			m[i][1] = scan.nextInt();
			scan.nextLine();
		}
		LongestSuq(str, m, N, M);
	}
	public static void LongestSuq(List<String> str, int m[][], int N, int M) {
		for (int i = 0; i < M; ++i) {
			String tmp = str.get(m[i][0]-1) + str.get(m[i][1]-1);
			for (int j = 1; j < tmp.length(); ++j) {
				backtrack(tmp, 0, i, "");
				if (flag) {
					k = i - 1;
					flag = false;
					break;
				}
			}
			System.out.println(k);
			str.add(tmp);
		}
	} 
	
	public static void backtrack(String tmp, int t, int n, String sub) {
		if (t == n) {
			if (sub != null && !tmp.contains(sub))
				flag = true;
			return;
		}

		backtrack(tmp, t+1, n, sub + '0');
		backtrack(tmp, t+1, n, sub+'1');
	}
}
