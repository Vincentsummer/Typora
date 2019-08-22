import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int low = scan.nextInt();
		int high = scan.nextInt();
		int result = minimumAdd(low, high);
		System.out.println(result);
	}
	
	public static int minimumAdd(int low, int high) {
		int single = 0, tens = 0;
		for (int i = low; i < high; ++i) {
			if (isPrimeNumber(i)) {
				single += i % 10;
				tens += (i / 10) % 10;
			}
		}
		return Math.min(single, tens);
	}
	
	public static boolean isPrimeNumber(int i) {
		int j = 2;
		while (j <= Math.sqrt(i)) {
			if (i % j == 0)
				return false;
			j++;
		}
		return true;
	}
}
