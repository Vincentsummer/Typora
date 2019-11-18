package DC;

/**
 * 654. 最大二叉树
 * **/

public class ConstructMaximumBinaryTree_654 {
	// 递归构建
    public TreeNode constructMaximumBinaryTree(int[] nums) {
    	return Backtrack(nums, 0, nums.length - 1, new TreeNode(0));
    }
    
    public TreeNode Backtrack(int nums[], int start, int end, TreeNode node) {
    	if (start > end)
    		return null;
        int maxValue = Integer.MIN_VALUE, maxIndex = -1;
    	for (int i = start; i <= end; i++) {
    		if (maxValue < nums[i]) {
    			maxValue = nums[i];
    			maxIndex = i;
    		}
    	}
    	node.val = maxValue;
    	node.left = Backtrack(nums, start, maxIndex - 1, new TreeNode(0));
    	node.right = Backtrack(nums, maxIndex + 1, end, new TreeNode(0));
    	
    	return node;
    }
    
class TreeNode{
	int val;
	TreeNode left, right;
	TreeNode(int x){val = x;}
}
}
