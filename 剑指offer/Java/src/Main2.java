import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) { 
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(), M = scan.nextInt();
		int grid[][] = new int[N][M];
		for (int i = 0; i < N; ++i) {
			scan.nextLine();
			for (int j = 0; j < M; ++j)
				grid[i][j] = scan.nextInt();
		}
		int result = fun(grid);
		System.out.println(result);
	}
	
	public static int fun(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0)
			return 0;
		int row = grid.length, col = grid[0].length;
		int result = 0;
		for (int i = 0; i < row; ++i) {
			for (int j = 0; j < col; ++j) {
				if (grid[i][j] > 0)
					result += 4 * grid[i][j] + 2;
				if (i > 0)
					result -= 2 * Math.min(grid[i][j], grid[i-1][j]);
				if (j > 0)
					result -= 2 * Math.min(grid[i][j], grid[i][j-1]);
			}
		}
		return result;
	}
}
