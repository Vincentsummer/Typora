import java.util.Scanner;

public class Main1 {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		scan.nextLine();
		int A[] = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = scan.nextInt();
		getNumber(A, N);
	}
	
	public static void getNumber(int A[], int N) {
		int aux[] = new int[N];
		int res = 0;
		for (int i = 0; i < N; ++i)
			aux[A[i]-1] = i;
			
		for (int i = 0; i < N; ++i) {
			if (A[i] != i+1) {
				res++;
				A[aux[i]] = A[i];
				aux[A[i] - 1] = aux[i];
				A[i] = i+1;
				aux[i] = i+1;
			}
		}
		System.out.println(res);
	}
}