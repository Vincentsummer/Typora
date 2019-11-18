package DP;

import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 ②
 * 给定一个整数 n，输出所有由 1 ... n 为节点所组成的二叉搜索树。
 * 思路如下：
 * 对于连续整数序列[left, right]中的一点i，若要生成以i为根节点的BST，则有如下规律：
 * i 左边的序列可以作为左子树结点，且左儿子可能有多个，有vector<TreeNode *> left_nodes = generate(left, i - 1);
 * i 右边的序列可以作为右子树结点，同上有vector<TreeNode *> right_nodes = generate(i + 1, right);
 * 产生的以当前i为根结点的BST（子）树有left_nodes.size() * right_nodes.size()个，遍历每种情况，即可生成以i为根节点的BST序列；
 * 然后以for循环使得[left, right]中每个结点都能生成子树序列。
 * **/

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

public class GenerateTrees2_95 {
	public List<TreeNode> generateTrees(int n) {
	    if(n == 0)
	        return new LinkedList<TreeNode>();
	    return generateTrees(1,n);
	}
	
	public List<TreeNode> generateTrees(int start,int end) {
	    List<TreeNode> res = new LinkedList<TreeNode>();
	    if(start > end){
	        res.add(null);
	        return res;
	    }
	    for(int i = start; i <= end; i++){
	        List<TreeNode> subLeftTree = generateTrees(start, i-1);
	        List<TreeNode> subRightTree = generateTrees(i+1, end);
	        for(TreeNode left : subLeftTree){
	            for(TreeNode right : subRightTree){
	                TreeNode node = new TreeNode(i);
	                node.left = left;
	                node.right = right;
	                res.add(node);
	            }
	        }                        
	    }
	    return res;
	} 
}
