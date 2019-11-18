package Others;

/**
 * 521. 最长特殊序列 Ⅰ
 * **/

public class FindLUSlength_521 {
	// 相等或不相等
    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }
}
