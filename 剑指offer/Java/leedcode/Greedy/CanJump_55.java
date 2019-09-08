package Greedy;

/**
 * 55. 跳跃游戏
 * **/

public class CanJump_55 {
	
	// 贪心算法：从后向前遍历，依次判断是否可以到达当前位置。
	// 事实上，若数组中不存在 0，则一定可以到达最后的位置，存在 0 则需考虑 0 之前的位置。
	 public boolean canJump(int[] nums) {
		 int step = 1;
		 for (int i = nums.length - 2; i >= 0; --i) {
			 if (nums[i] >= step)
				 step = 1;
			 else
				 step++;
		 }
		 return step == 1;
	 }
	
	// 动态规划，record[i] 记录能否到达 i。
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        boolean record[] = new boolean[n];
        for (int i = 0; i < n; ++i) {
        	if (i == 0 && nums[i] != 0)
        		record[i] = true;
        	for (int j = 0; j < i; ++j) {
        		if (nums[j] >= i - j) {
        			record[i] = true;
        			break;
        		}
        	}
        }
        return record[n-1];
    }
}
