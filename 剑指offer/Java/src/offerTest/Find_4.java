package offerTest;
/**
 * ����������
 * 1. ��ά�����а������ҵ����֡�
 * 2. ��ά������û�в��ҵ����֡�
 * 3. ����������ԣ������ָ�룩��
 * **/
public class Find_4 {

	public static void main(String[] args) {
	int a[][] = {};

//	System.out.println("rows: " + rows +  " , columns: " + columns);
	boolean found = find(a, 5);
	System.out.println(found);  
	}
	
	public static boolean find(int[][] matrix, int number) {

		if (matrix != null && matrix.length > 0) {
			int rows = matrix[0].length, columns = matrix.length;
			int row = 0, column = columns - 1;
			while (row < rows && column >= 0) {
				if (matrix[row][column] == number) {
					return true;
				}
				else if (matrix[row][column] > number)
					column--;
				else
					row++;
			}
		}
		return false;
	}
}
