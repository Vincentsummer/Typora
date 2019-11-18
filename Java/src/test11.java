/**
 * 闹钟
 * **/
import java.util.Scanner;

public class test11 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.nextLine();
		int[][] clock = new int[N][2];
		for (int i = 0; i < N; ++i) {
			clock[i][0] = scan.nextInt();
			clock[i][1] = scan.nextInt();
			scan.nextLine();
		}
		int X = scan.nextInt();
		scan.nextLine();
		int A = scan.nextInt();
		int B = scan.nextInt();
		
		int m = B - X;
		int h = A;
		while (m < 0) {
			m += 60;
			h--;
		}
		int i = N-1;
		for (; i >= 0; --i) {
			if (h > clock[i][0] || (h == clock[i][0] && m >= clock[i][1]))
				break;
		}
		System.out.println(clock[i][0] + " " + clock[i][1]);
	}
}
