package Others;
/**
 * 3. 无重复字符的最长子串
 * **/
public class LengthOfLongestSubstring_3 {
    public int lengthOfLongestSubstring(String s) {
    	int len = s.length();
    	if (len == 0) return 0;
    	if (len == 1) return 1;
    	String str = "";
    	int res = 0, tmp = 0;
    	for (int i = 0; i < len; ++i) {
    		if (!str.contains(String.valueOf(s.charAt(i)))) {
    			str += s.charAt(i);
    			tmp ++;
    		}
    		else {
				int idx = str.indexOf(s.charAt(i));
				str = str.substring(idx+1);
				str += s.charAt(i);
				tmp = str.length();
			}
    		res = Math.max(res, tmp);
    	}
    	return res;
    }
}
