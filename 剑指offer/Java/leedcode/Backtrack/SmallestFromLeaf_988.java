package Backtrack;
/**
 * 988. 从叶结点开始的最小字符串
 * **/
public class SmallestFromLeaf_988 {
	private String res = "";
	// DFS
    public String smallestFromLeaf(TreeNode root) {
    	if (root == null) return "";
    	backtrack(root, "");
    	return res;
    }
    
    public void backtrack(TreeNode node, String str) {
    	str = (char) (node.val + 'a') + str;
    	if (node.left == null && node.right == null) {
    		if (res.isEmpty() || res.compareTo(str) > 0)
    			res = str;
    		return;
    	}
    	if (node.left != null) backtrack(node.left, str);
    	if (node.right != null) backtrack(node.right, str);
    }
}