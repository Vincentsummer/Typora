package Others;

/**
 * 553. 最优除法
 * **/

public class OptimalDivision_553 {
	// 一组括号把从第二个数开始的所有数括起来
    public String optimalDivision(int[] nums) {
        if(nums.length == 0){
            return "";
        }
        else if(nums.length == 1){
            return nums[0] + "";
        }
        else if(nums.length == 2){
            return nums[0] + "/" + nums[1];
        }
        String ans = nums[0] + "/(";
        for(int i = 1; i < nums.length - 1; i++){
            ans += nums[i] + "/";
        }
        ans += nums[nums.length - 1] + ")";
        return ans;
    }
}
