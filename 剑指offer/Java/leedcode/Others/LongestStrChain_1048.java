package Others;
/**
 * 1048. 最长字符串链
 * **/
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestStrChain_1048 {
    public int longestStrChain(String[] words) {
        return findLongest(words);
    }
    
    private int findLongest(String[] strs){
        /**
         * 将字符串数组按长度排序
         */
        List<String> list = Arrays.asList(strs);
        list.sort(Comparator.comparingInt(String::length));
        String[] strings = (String[]) list.toArray();

        int[] dp = new int[strings.length];
        int max = 0;

        for(int i=1;i<strings.length;i++){
            for(int j=0;j<i;j++){
                //step1:排除字符串长度差不为1的字符串,不加step1会超时
                //step2:判断排除任意一个字符的字符串strings[i]是否和strings[j]相等
                if((strings[i].length() == strings[j].length()+1) && (containsEverySingleWord(strings[i],strings[j]))){
                    dp[i] = dp[j]+1;
                    max = Math.max(max,dp[i]);
                }
            }
        }
        return max+1;
    }
    
    /**
     * 如果源字符串排除任意一个位置后与要比对的字符串相等，返回true
     */
    private boolean containsEverySingleWord(String original, String tofind) {

        for(int i=0;i<original.length();i++){
            String tmp = original.substring(0,i)+original.substring(i+1);
            if(tmp.equals(tofind)){
                return true;
            }
        }
        return false;
    }
}
