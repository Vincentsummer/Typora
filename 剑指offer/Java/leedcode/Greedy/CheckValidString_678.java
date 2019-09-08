package Greedy;
/**
 * 678. 有效的括号字符串
 * **/
public class CheckValidString_678 {
	// 双指针，low表示当前位置左括号可能的最小的数量，high表示当前位置左括号可能的最多的数量。
	// 遍历过程中，若碰到右括号且high为0，则false。
	// 遍历完成后，若low不为0，则false。
	// O(n) & O(1)
    public boolean checkValidString(String s) {
        if (s == null) return true;
        int low = 0, high = 0;
        for (int i = 0; i < s.length(); ++i) {
        	if (s.charAt(i) == '(') {low++; high++;}
        	else if (s.charAt(i) == '*') {
        		if (low > 0)	low--;
        		high++;
        	}
        	else {
                if (high == 0) return false;
                if (low > 0) low--;
                high--;
        	}
        }
        if (low != 0) return false;
        return true;
    }
}
