package DC;
/**
 * 540. 有序数组中的单一元素
 * **/
public class SingleNonDuplicate_540 {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length, res = 0;
        int low = 0, high = len - 1;
        while (low <= high){
            int middle = (low + high) / 2;
            if (middle < high && nums[middle] == nums[middle+1]){
                if ((high - middle - 1) % 2 == 0)
                    high = middle - 1;
                else low = middle + 2;
            }
            else if (middle > 0 && nums[middle] == nums[middle-1]){
                if ((middle - low - 1) % 2 == 0)
                    low = middle + 1;
                else high = middle - 2;
            }
            else{
                res = nums[middle];
                break;
            }
        }
        return res;
    }
}
