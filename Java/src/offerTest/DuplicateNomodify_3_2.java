package offerTest;
/** ��������
	1. ����Ϊn�����������һ�������ظ������֡�
	2. �����в������ظ������֡�
	3. ��Ч������������������ָ�룩��
**/

public class DuplicateNomodify_3_2 {

	public static void main(String[] args) {
		int[] a = {1, 2, 4, 3, 5};
		int start = 1, end = a.length - 1;
		int len = a.length;
		int result = getDuplicateWithRecursion(a, len, start, end);
//		int result = getDuplicateWithRecursion(a, len);
		System.out.println(result);
		
	}
	
	public static int getDuplicateWithRecursion(int[] numbers, int length, int start, int end) {
		if (numbers == null || length <=0 || start <= 0 || end <= 0)
			return -1;
		int count = getCount(numbers, length, start, end);
		if (start == end) {
			if (count > 1)
				return start;
			else
				return -1;
		}
		
		int middle = ((end - start) >> 1) + start;
		
		if (getCount(numbers, length, start, middle) > middle - start + 1)
			return getDuplicateWithRecursion(numbers, length, start, middle);
		else
			return getDuplicateWithRecursion(numbers, length, middle + 1, end);
		
	}
	
	public static int getDuplicate(int[] numbers, int length) {
		if (numbers == null || length <= 0)
			return -1;
		
		int start = 1, end = length - 1;
		while(start <= end) {
			int middle = ((end - start) >> 1) + start;
			int count = getCount(numbers, length, start, middle);
			if (end == start) {
				if (count > 1)
					return start;
				else
					break;
			}
			
			if (count > (middle - start + 1))
				end = middle;
			else
				start = middle + 1;
		}
		return -1;
	}
	
	public static int getCount(int[] numbers, int length, int start,  int end) {
		
		if (numbers == null)
			return 0;
		
		int result = 0;
		for (int i = 0; i < length; ++i) {
			if (numbers[i] >= start && numbers[i] <= end)
				result++;
		}
		return result;
	}

}
