package Others;

/**
 * 35. 搜索插入位置
 * **/

public class SearchInsert_35 {
	// 二分查找
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int middle = 0;
        while(left <= right) {
        	middle = (left + right) / 2;
        	if (nums[middle] == target)
        		return middle;
        	else if (nums[middle] > target)
        		right = middle - 1;
        	else
        		left = middle + 1;
        }
        return nums[middle] < target ? middle + 1 : (middle == 0 ? 0 : middle);
    }
}
