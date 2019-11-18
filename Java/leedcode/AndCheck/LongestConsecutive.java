package AndCheck;

/**
 * 128.  最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * **/

import java.util.HashMap;

public class LongestConsecutive {
	/**
	 * 使用哈希map存储数组中每个数字(Key)所在最长连续序列的长度(Value)，每遍历一个数字，有以下几种情况：
	 * 1. 数字不在Hashmap中，则存入。（存在则无须理会）
	 * 2. 对于每一个数字，在Hashmap中查找是否存在其相邻数字，若存在则需要更新其Value值，以及其所在序列端点的Value值。
	 * 3. 记录最大的长度。
	 * 复杂度 O(n) & O(n)
	 * **/
    public int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (n  == 0)
        	return 0;
        HashMap<Integer, Integer> valMap = new HashMap<>();
        int result = 0, left = 0, right = 0, len = 1;
        for (int i = 0; i < n; ++i) {
        	// 已处理，不理会
        	if (valMap.containsKey(nums[i]))
        		continue;
        	
        	// 左右相邻数字都存在，更新
			if (valMap.containsKey(nums[i] - 1) && valMap.containsKey(nums[i] + 1)) {
				left = valMap.get(nums[i] - 1);
				right = valMap.get(nums[i] + 1);
				len = left + right + 1;
				valMap.put(nums[i], len);
				valMap.put(nums[i]-left, len);
				valMap.put(nums[i]+right, len);
			}
			// 只左邻存在， 更新
			else if (valMap.containsKey(nums[i] - 1)){
				left = valMap.get(nums[i] - 1);
				len = left + 1;
				valMap.put(nums[i], len);
				valMap.put(nums[i]-left, len);
			}
			// 只右邻存在，更新
			else if (valMap.containsKey(nums[i] + 1)){
				right = valMap.get(nums[i] + 1);
				len = right + 1;
				valMap.put(nums[i], len);
				valMap.put(nums[i]+right, len);
			}
			// 都不存在，更新
            else
                valMap.put(nums[i], 1);
			// 记录最大长度
			result = Math.max(result, len);
        }
        return result;
    }
}
