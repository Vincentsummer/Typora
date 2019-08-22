import java.util.Scanner;

public class Main1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str[] = scan.nextLine().split(",");
		int maxLen = 0;
		for (int i = 0; i < str.length; ++i)
			maxLen = Math.max(maxLen, str[i].length());
		reverseSort(str, maxLen);
	}
	
	public static void reverseSort(String str[], int maxLen) {}
}