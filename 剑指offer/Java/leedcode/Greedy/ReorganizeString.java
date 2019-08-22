package Greedy;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * **/

public class ReorganizeString {
    /**
     * 思路如下：
     *  找出出现频率最高的char，然后要把他们分隔开, 隔一个slot插值
     *  对于其他所有的剩余字符，也是隔一个插入，因为长度小于一半，所以肯定不会重复
     */
    public String reorganizeString(String s){
        if (s == null || s.length() == 0) {
            return "";
        }
        //词频统计
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        //寻找最大频率的字母
        int maxCount = 0;
        char letter = 0;
        for (int i = 0; i < 26; i++) {
            if (maxCount < count[i]) {
                maxCount = count[i];
                letter = (char) ('a' + i);
            }
        }
        //如果最大频率大于一半+1，则无法完成任务
        if (maxCount > (1 + s.length()) / 2) {
            return "";
        }

        //结果集
        char[] res = new char[s.length()];
        //index 维护下一个应该插入的位置
        //隔空插入最大频率字母
        int index = insert(count, res, 0, letter);

        //插入其他字母
        for (int i = 0; i < 26; i++) {
            index = insert(count, res, index, (char)('a' + i));
        }
        return new String(res);
    }

    private int insert(int[] count, char[] res, int index, char letter) {
        int i = letter - 'a';
        while (count[i] > 0) {
            //因为index大于res长度的情况是之前从0开始间隔2插入了频率最高的字符，
            // 所以这里应该是从0和2之间的间隔开始
            if (index >= res.length) {
                index = 1;
            }
            res[index] = letter;
            count[i]--;
            index += 2;
        }
        //返回下一个能插入的位置
        return index;
    }
}
