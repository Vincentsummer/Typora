package Others;

public class SortColors_75 {
	// 思路：一趟三向快排
    public void sortColors(int[] nums) {
        int left = -1, right = left;
        for (int i = 0;  i < nums.length; ++i) {
            if(nums[i] < 2)
            {
                swap(nums,i,++right);
                if(nums[right] < 1)
                    swap(nums, ++left, right);
            }
        }
    }
    
    public void swap(int nums[], int idx1, int idx2) {
    	int tmp = nums[idx1];
    	nums[idx1] = nums[idx2];
    	nums[idx2] = tmp;
    }
}
