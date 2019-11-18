package vTree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BiTreeOp implements AbstractBiTree{
	private BinaryTreeNode root = new BinaryTreeNode();
	
	public BiTreeOp(String tree) {
		String[] nodes = tree.split(",");
		createBiTree(nodes);
	}
	
	public BinaryTreeNode getTreeNode() {
		return root;
	}
	
	// 递归创建二叉树，先序
	public int createBiTreeByRecursion(BinaryTreeNode node, String[] nodes, int n) {
		if (nodes[n].equals("#"))
			return n+1;
		node.val = nodes[n];
		
		node.left = new BinaryTreeNode();
		int left = createBiTreeByRecursion(node.left, nodes, n+1);
		node.right = new BinaryTreeNode();
		int right = createBiTreeByRecursion(node.right, nodes, left);
		
		return right;
	}
	
	public void createBitreeByRecursion(String[] nodes) {
		createBiTreeByRecursion(root, nodes, 0);
	}
	
	// 非递归创建，先序
	public void createBiTree(String[] nodes) {
		Stack<BinaryTreeNode> aux = new Stack<>();
		int i = 0;
		boolean flag = false;
		BinaryTreeNode node = root;
		while (i < nodes.length) {
			while (true) {
				if (nodes[i].equals("#")) {
					node = aux.pop();
					if (!node.visited) {
						node.visited = true;
						node.left = null;
					}
					else
						node.right = null;
					i++;
					break;
				}
			}
			if (node.visited) {
				node.right = new BinaryTreeNode(nodes[i]);
				node = node.right;
			}
			aux.push(node);
			node.left = new BinaryTreeNode();
			node = node.left;
			i++;
		}
	}
	
	public void printBiTreeNode(BinaryTreeNode node) {
		if (node.val == null)
			System.out.println("");
		else
			System.out.println(node.val);
	}
	
	// 先序递归遍历
	private void printPreOrderByRecursion(BinaryTreeNode node) {
		if (node == null)
			return;
		printBiTreeNode(node);
		printPreOrderByRecursion(node.left);
		printPreOrderByRecursion(node.right);
	}

	@Override
	public void printPreOrderByRecursion() {
		printPreOrderByRecursion(root);
	}
	
	// 中序递归遍历
	public void printInOrderByRecursion(BinaryTreeNode node) {
		if (node == null)
			return;
		printInOrderByRecursion(node.left);
		printBiTreeNode(node);
		printInOrderByRecursion(node.right);
	}
	
	@Override
	public void printInOrderByRecursion() {
		printInOrderByRecursion(root);
	}
	
	// 后序递归遍历
	public void printPostOrderByRecursion(BinaryTreeNode node) {
		if (node == null)
			return;
		printInOrderByRecursion(node.left);
		printInOrderByRecursion(node.right);
		printBiTreeNode(node);
	}

	@Override
	public void printPostOrderByRecursion() {
		printPostOrderByRecursion(root);
	}

	// 非递归遍历二叉树
	@Override
	public void printPreOrder() {
		// 先序遍历
		Stack<BinaryTreeNode> aux = new Stack<>();
		BinaryTreeNode node = root;
		while (true) {
			while (node != null) {
				printBiTreeNode(node);
				aux.push(node);
				node = node.left;
			}
			if (aux.isEmpty())
				break;
			node = aux.pop();
			node = node.right;
		}
	}

	@Override
	public void printInOrder() {
		// 中序遍历
		Stack<BinaryTreeNode> aux = new Stack<>();
		BinaryTreeNode node = root;
		
		while(true) {
			while (node != null) {
				aux.push(node);
				node = node.left;
			}
			
			if (aux.isEmpty())
				break;
			node = aux.pop();
			printBiTreeNode(node);
			node = node.right;
		}
	}
	
	@Override
	public void printPostOrder() {
		// 后序遍历
		Stack<BinaryTreeNode> aux = new Stack<>();
		BinaryTreeNode node = root;
		
		while(true) {
			while (node != null) {
				if (node.visited)
					node = node.right;
				else {
					aux.push(node);
					node = node.left;
				}
			}
			node = aux.pop();
			if (!node.visited) {
				aux.push(node);
				node.visited = true;
				node = node.right;
			}
			else {
				printBiTreeNode(node);
				if (aux.isEmpty())
					break;
				node = aux.peek();
				node.visited = true;
			}
		}
	}

	@Override
	public void printLevelOrder() {
		// 层序遍历，利用队列
		Queue<BinaryTreeNode> aux = new LinkedList<>();
		BinaryTreeNode node = root;
		aux.offer(node);
		while(aux.isEmpty()) {
			BinaryTreeNode topNode = aux.poll();
			
			if (topNode == null)
				continue;
			printBiTreeNode(topNode);
			aux.offer(topNode.left);
			aux.offer(topNode.right);
		}
	}
	
	// 树高 递归，分别求出左子树的深度、右子树的深度，两个深度的较大值+1
	public int getHeightByRecursion(BinaryTreeNode node) {
		if (node == null)
			return 0;
		int left = getHeightByRecursion(node.left);
		int right = getHeightByRecursion(node.right);
		return 1 + Math.max(left, right);
	}
	
	@Override
	public void printHeight() {
		int height = getHeightByRecursion(root);
		System.out.println(height);
	}

	@Override
	public void printMaxWidth() {
		// 利用层序遍历,得到树的最大宽度

		Queue<BinaryTreeNode> queue = new LinkedList<>();
		Queue<BinaryTreeNode> queueTemp = new LinkedList<>();
 
		int maxWidth = 1;
 
		BinaryTreeNode tempNode = root;
 
		queue.offer(tempNode);
 
		while (!queue.isEmpty()) {
 
			while (!queue.isEmpty()) {
 
				BinaryTreeNode topNode = queue.poll();
 
				if (topNode == null)
					continue;
 
				if (topNode.left.val != null)
					queueTemp.offer(topNode.left);
 
				if (topNode.right.val != null)
					queueTemp.offer(topNode.right);
			}
 
			maxWidth = Math.max(maxWidth, queueTemp.size());
			queue = queueTemp;
			queueTemp = new LinkedList<>();
		}
 
		System.out.print(maxWidth);
	}
}
