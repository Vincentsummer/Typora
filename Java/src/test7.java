import java.util.Scanner;

public class test7 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N  = scan.nextInt();
		int number[] = new int[N];
		scan.nextLine();
		for (int i = 0; i < N; ++i) 
			number[i] = scan.nextInt();
		int result = minAdd(number, N);
		System.out.println(result);
	}

	public static int minAdd(int number[], int N) {
		int result = 0;
		boolean flag = false;
		int i = 1, tmp = 0;
		while (i < N) {
			if (!flag && number[i-1] >= number[i]) {
				tmp = number[i-1] - number[i] + 1;
				number[i-1] += tmp;
				result += tmp;
				i--;
			}
			if  (flag && number[i -1] <= number[i]) {
				tmp = number[i] - number[i-1] + 1;
				number[i] += tmp;
				result += tmp;
				i--;
			}
			if (number[i - 1] > number[i] && !flag)
				flag = true;
			i++;
		}
		return result;
	}
}
