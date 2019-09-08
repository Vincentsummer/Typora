package Backtrack;

/**
 * 814. 二叉树剪枝
 * **/

public class PruneTree_814 {
	// 回溯法，DFS
    public TreeNode pruneTree(TreeNode root) {
        Backtrack(root);
        return root;
    }
    
    public boolean Backtrack(TreeNode node) {
    	if (node == null)
    		return false;
    	
    	if (Backtrack(node.left))
    		node.left = null;
    	if (Backtrack(node.right))
    		node.right = null;
    	
    	if (node.left == null && node.right == null && node.val == 0)
    		return true;
    	return false;
    }
}