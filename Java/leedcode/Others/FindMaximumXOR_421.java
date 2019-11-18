package Others;

/**
 * 421. 数组中两个数的最大异或值
 * **/

import java.util.HashSet;
import java.util.Set;

public class FindMaximumXOR_421 {
	/**
	 * 思路一：从左高位进行计算，用一个掩码mask来作为底，其中mask当前位i到左高位的所有位置上的数字为1，
	 * 其它位置为0，即11…10…0。将数组中的每个元素分别与mask进行与运算，将与运算的结果存进集合set中。 
	 * 然后利用a ^ b = c，而a ^ b ^ b = a, 则 c ^ b = a的异或关系，找出每一位异或结果是否可以为1。
	 * **/
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for (int i = 31; i >= 0; --i) {
        	mask |= (1 << i);
        	Set<Integer> reSet = new HashSet<>();
        	for (int n : nums)
        		reSet.add(mask & n);
        	int tmp = max | (1 << i);
        	for (int res : reSet) {
        		if (reSet.contains(res ^ tmp)) {
        			max = tmp;
        			break;	
        		}
        	}
        }
        return max;
    }
    
    /**
     * 思路二：字典树
     * Tire树,利用nums中的数构建tire树,然后依次查找数组nums中数的异或最大值。
     * **/
    public int findMaximumXOR1(int[] nums) {
    	TreeNode root = new TreeNode(-1);
    	int max = 0;
    	buildTree(nums, root);
    	for (int n : nums) {
    		TreeNode node = root;
    		for (int i = 31; i >=0; --i) {
    			if ((n & (1 << i)) == 0)
    				node = node.right == null ? node.left : node.right;
    			else
    				node = node.left == null ? node.right : node.left;
    		}
    		int res = node.left.val;
    		max = Math.max(max, n ^ res);
    	}
    	return max;
    }
    
    public void buildTree(int[] nums, TreeNode root) {
    	for (int n : nums) {
    		TreeNode node = root;
    		for (int i = 31; i >= 0; --i) {
    			if ((n & (1 << i)) == 0) {
    				if (node.left == null)
    					node.left = new TreeNode(0);
    				node = node.left;
    			}
    			else {
					if (node.right == null)
						node.right = new TreeNode(1);
					node = node.right;
				}
    		}
    		node.left = new TreeNode(n);
    	}
    }
    
    static class TreeNode{
    	int val;
    	TreeNode left;	// 0
    	TreeNode right;	// 1
    	TreeNode(int x){val = x;} 
    }
}
