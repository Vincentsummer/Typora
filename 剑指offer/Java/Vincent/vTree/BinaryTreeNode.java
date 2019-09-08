package vTree;

public class BinaryTreeNode {
	public String val;
	public BinaryTreeNode left = null;
	public BinaryTreeNode right = null;
	public boolean visited;
	public BinaryTreeNode(String x) {
		val = x;
	}
	public BinaryTreeNode() {}
}
