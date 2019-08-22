import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		String str[] = new String[n];
		for (int i = 0; i < n; ++i)
			str[i] = scan.nextLine();
		while (scan.hasNextLine()) {
			int index1 = scan.nextInt();
			int index2 = scan.nextInt();
			if (index1 > n || index2 > n)
				continue;
			String str1 = str[index1-1];
			String str2 = str[index2-1];
			maxPrior(str1, str2);
		}
	}
	public static void maxPrior(String str1, String str2) {
		int j = 0, result = 0;
		while (j <str1.length() && j < str2.length()) {
			if (str1.charAt(j) != str2.charAt(j))
				break;
			else 
				result++;
			j++;
		}
		System.out.println(result);
	}
}
