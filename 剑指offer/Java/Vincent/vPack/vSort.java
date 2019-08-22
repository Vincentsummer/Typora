package vPack;

public class vSort {

	/**
	 *  插入排序
	 * **/
	public void insertSort(int[] A) {
		for (int i = 1; i < A.length; ++i) {
			int key = A[i];
			int j = i - 1;
			while (j >= 0 && A[j] > key) {
				A[j+1]  = A[j];
				j--;
			}
			A[j+1] = key;
		}
	}
	
	/**
	 * 归并排序
	 * **/ 
	public void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q+1, r);
			merge(A, p, q, r);
		}
	}
	
	private void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1, n2 = r - q;
		int L[] = new int[n1+1];
		int R[] = new int[n2+1];
		for (int i = 0; i < n1; ++i)
			L[i] = A[p + i];
		
		for (int i = 0; i < n2; ++i)
			R[i] = A[q+i+1];
		
		L[n1] = Integer.MAX_VALUE;
		R[n2] = Integer.MAX_VALUE;
		
		int i = 0, j = 0;
		for (int k = p; k <= r; ++k) {
			if (L[i] < R[j]) {
				A[k] = L[i];
				i++;
			}
			else {
				A[k] = R[j];
				j++;
			}
		}
	}
	
	/**
	 * 快速排序
	 * **/ 
	public void quickSort(int[] A, int p, int r) {
		if (p < r) {
			int q = partition(A, p, r);
			quickSort(A, p, q-1);
			quickSort(A, q+1, r);
		}
	}
	
	private int partition(int[] A, int p, int r) {
		int x = A[r], i = p -1;
		for (int j = p; j <r; ++j) {
			if (x >= A[j])
				swap(A, ++i, j);	
		}
		swap(A, ++i, r);
		return i;
	}
	
	private void swap(int A[], int index1, int index2) {
		int tmp = A[index1];
		A[index1] = A[index2];
		A[index2] = tmp;
	}
	
	/**
	 * 堆排序
	 * **/ 
	private int parent(int index) {
		return index / 2;
	}
	
	private int leftChild(int index) {
		return index * 2;
	}
	
	private int rightChild(int index) {
		return index * 2 + 1;
	}
	
	//  维护以 i 为根节点的 A 的子数组的最大堆的性质
	private void maxHeapify(int A[], int i, int heapSize) {
		// 数组下标以 0 开始，与堆有所出入
		int j = i +1, largest = 0;
		int left = leftChild(j) - 1, right = rightChild(j) - 1;
		if (left < heapSize && A[left] > A[i])
			largest = left;
		else
			largest = i;
		if (right < heapSize && A[right] > A[largest])
			largest = right;
		
		if (largest != i) {
			swap(A, i, largest);
			maxHeapify(A, largest, heapSize);
		}
	}
	
	// 建最大堆
	private void buildMaxHeap(int A[], int heapSize) {
		for (int i = (heapSize - 1) / 2; i >= 0; --i)
			maxHeapify(A, i, heapSize);
	}
	
	// 堆排序 
	// 通过将数组中的最大元素（根结点A[0]）与 A[n-1] 进行互换，
	// 并且每次从堆中去掉结点 n-1，剩余结点中，原来根的结点仍然是最大堆，
	// 而新的根结点可能会违背最大堆的性质，调用maxHeapify。
	public void heapSort(int A[]) {
		int heapSize = A.length;
		buildMaxHeap(A, heapSize);
		for (int i = A.length - 1; i >0; --i) {
			swap(A, 0, i);
			heapSize--;
			maxHeapify(A, 0, heapSize);
		}
	}
}
