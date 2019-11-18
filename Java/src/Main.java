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
 	}
}