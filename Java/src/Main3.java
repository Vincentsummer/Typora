import java.util.Scanner;

public class Main3 {
	public static int res;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		int q = scan.nextInt();
		scan.nextLine();
		String str = scan.nextLine();
		int A[][] = new int[q][2];
		for (int i = 0; i < q; ++i) {
			A[i][0] = scan.nextInt() - 1;
			A[i][1] = scan.nextInt() - 1;
			scan.nextLine();
		}
		
		for (int i = 0; i < q; ++i) {
			res = 0;
			String tmp = new String(str);
			backtrack(tmp.split(" "), A[i][0], A[i][1], A[i][0], false, 1, A[i][1] - A[i][0] + 1);
			System.out.println(res);
		}
	}

	public static void backtrack(String s[], int left, int right, int pos, boolean arraw, int flag, int count) {
		if (count == 0 || pos < left || pos > right) return;
		
		if (s[pos].equals("#"))
			backtrack(s, left, right, pos + flag, arraw, flag, count);
		
		else if (s[pos].equals("<")) {
			if (arraw) {
				s[pos] = "#";
				backtrack(s, left, right, pos+flag, arraw, flag, count-1);
			}
			else 
				backtrack(s, left, right, pos-1, true, -1, count);
		}
		else if (s[pos].equals(">")) {
			if (arraw) {
				s[pos] = "#";
				backtrack(s, left, right, pos+flag, arraw, flag, count-1);
			}
			else
				backtrack(s, left, right, pos+1, true, 1, count);
		}
		else {
			if (s[pos].equals("0")) {
				s[pos] = "#";
				backtrack(s, left, right, pos+flag, arraw, flag, count-1);
			}
			else {
				res += Integer.parseInt(s[pos]);
				s[pos] = String.valueOf(Integer.parseInt(s[pos])-1);
				backtrack(s, left, right, pos+flag, false, flag, count);
			}
		}
	}
}
