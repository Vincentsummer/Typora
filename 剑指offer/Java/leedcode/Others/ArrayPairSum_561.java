package Others;

/**
 * 561. 数组拆分 I
 * **/

import java.util.Arrays;

public class ArrayPairSum_561 {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i+=2)
            res += nums[i];
        return res;
    }
}
