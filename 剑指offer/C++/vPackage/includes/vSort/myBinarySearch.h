#ifndef VSORT_MYBINARYSEARCH_H_
#define VSORT_MYBINARYSEARCH_H_

// ���ֲ��ң� �����ڣ� �������� a �� k ���±꣬�������ڣ�����-1��
int binarySearch(int k, int a[], int length){
	int left = 0, right = length - 1;
	while(left <= right){
		int middle = (left + right) / 2;
		if (a[middle] == k)
			return middle;
		else if (a[middle] > k)
			right = middle - 1;
		else
			left = middle + 1;
	}
	return -1;
}



#endif /* VSORT_MYBINARYSEARCH_H_ */
