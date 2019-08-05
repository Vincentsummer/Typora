#ifndef MYSORT_MYBINARYSEARCH_H_
#define MYSORT_MYBINARYSEARCH_H_

// 二分查找， 若存在， 返回数组 a 中 k 的下标，若不存在，返回-1；
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



#endif /* MYSORT_MYBINARYSEARCH_H_ */
