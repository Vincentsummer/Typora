package Others;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class preorderTraversal_144 {
	// 迭代求解二叉树的前序遍历
    public List<Integer> preorderTraversal(TreeNode root) {
    	Stack<TreeNode> aux = new Stack<>();
    	List<Integer> res = new ArrayList<>();
    	if (root == null) return res;
    	aux.push(root);
    	while(!aux.isEmpty()) {
    		TreeNode node = aux.pop();
    		res.add(node.val);
    		if (node.right != null)
    			aux.push(node.right);
    		if (node.left != null)
    			aux.push(node.left);
    	}
    	return res;
    }
}

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x) {val = x;}
}