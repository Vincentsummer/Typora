package Others;

/**
 * 283. 移动零
 * **/

public class MoveZeroes_283 {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int j = -1;
        for (int i = 0; i < len; ++i) {
        	if (nums[i] == 0) {
        		j++;
        		continue;
        	}
        	if (j != -1){
        		nums[i-j] = nums[i];
        		nums[i] = 0;
        	}
        }
    }
}
