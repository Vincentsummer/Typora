
public class Duplicate_3_1 {

	public static void main(String[] args) {
		int[] a = {};
		int len = a.length;
		int result = duplicate(a, len);
		System.out.println(result);

	}
	
	public static int duplicate(int numbers[], int length) {
		if (numbers == null || length <= 0)
			return -1;
		
		for (int i = 0; i < length;  ++i) {
			if (numbers[i] < 0 || numbers[i] > length - 1)
				return -1;
		}
		
		for (int i = 0; i < length;  ++i) {
			while(numbers[i] != i) {
				if (numbers[i] == numbers[numbers[i]]) {
					int duplication = numbers[i];
					return duplication;
				}
				
				int temp = numbers[i];
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return -1;
	}
}
