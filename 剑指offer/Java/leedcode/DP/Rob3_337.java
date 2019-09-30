package DP;
/**
 * 337. 打家劫舍 III
 * **/
public class Rob3_337 {
    //递归思想（不要深入递归函数体，只需知道递归函数的功能，以及找到跳出递归的边界条件）
    //思路：
    //能盗取的最高金额为 抢劫该节点+抢劫该节点的左孩子的左右子树+抢劫该节点的右孩子的左右子树
    //与 抢劫该节点的左子树+抢劫该节点的右子树的和  的最大值
    //执行用时 1005ms  原因是出现了很多重复的计算，可使用动态规划解决
    
    //动态规划
    //思路：
    //定义一个数组res,长度为2,res[0]表示不抢该节点可获得最大值,res[1]表示抢劫该节点可获得最大值
    //方法helper(r)意为：在以r为根节点的树中,返回抢劫根节点与不抢劫根节点可获得的最大值
    //执行用时 2ms
    public int rob(TreeNode root) {
        int[] res = backtrack(root);
        return Math.max(res[0],res[1]);
    }

    public int[] backtrack(TreeNode node){
        if(node == null) return new int[2];
        int[] left = backtrack(node.left);
        int[] right = backtrack(node.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = node.val + left[0] + right[0];
        return res;
    }
}
