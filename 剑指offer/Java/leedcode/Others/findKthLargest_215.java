package Others;
/**
 * 215. 数组中的第K个最大元素
 * **/
public class findKthLargest_215 {
	// 快速选择
    public int findKthLargest(int[] nums, int k){
        // k是要求的第几大(从1开始计数),k-1即是数组中的序号(0开始计数)
        return findKthLargest(nums, 0, nums.length-1, k-1);
    }
    public int findKthLargest(int[] nums, int low, int high, int k){
        int index = partition(nums, low, high, k);
        if (index == k)
            return nums[index];
        else if (index > k)
            return findKthLargest(nums, low, index-1, k);
        else
            return findKthLargest(nums, index+1, high, k);
    }

    // 同快排的partation,每次确定一个枢轴的位置,并返回其index
    // 找第k大的数就把数组按大到小排列
    private int partition(int[] nums,int low,int high,int k){
        int pivot = nums[high];
        int i = low - 1, j = low;
        while (j < high) {
        	if (pivot < nums[j])
        		swap(nums, ++i, j);
        	j++;
        }
        swap(nums, ++i, high);
        return i;
    } 
    
    private void swap(int nums[], int idx1, int idx2) {
    	int tmp = nums[idx1];
    	nums[idx1] = nums[idx2];
    	nums[idx2] = tmp;
    }
}
