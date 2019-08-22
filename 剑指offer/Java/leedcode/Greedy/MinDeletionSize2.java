package Greedy;

/**
 * 955. 删序造列
 * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
 * 选取一个删除索引序列，对于 A 中的每个字符串，删除对应每个索引处的字符。
 * 比如，有 A = ["abcdef", "uvwxyz"]，删除索引序列 {0, 2, 3}，删除后 A 为["bef", "vyz"]。
 * 假设，我们选择了一组删除索引 D，那么在执行删除操作之后，最终得到的数组的元素是按 
 * 字典序（A[0] <= A[1] <= A[2] ... <= A[A.length - 1]）排列的，然后请你返回 D.length 的最小可能值。
 * **/

public class MinDeletionSize2 {
    public static int minDeletionSize(String[] strs){
        if (strs == null || strs.length < 2) {
            return 0;
        }
        int arrLen = strs.length;
        int strLen = strs[0].length();
        // 如果cuts[i] = true， 那么我们不需要再检查 strs[i][j] > strs[i+1][j]
        boolean[] cuts = new boolean[arrLen];
        int ans = 0;
        boolean delete;

        for (int j = 0; j < strLen; j++) {
            delete = false;
            for (int i = 0; i < arrLen - 1; i++) {
                if (!cuts[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    ans++;
                    delete = true;
                    break;
                }
            }
            if (!delete) {
                // 更新cuts
                for (int i = 0; i < arrLen - 1; i++) {
                    if (strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                        cuts[i] = true;
                    }
                }
            }
        }
        return ans;
    }
}
