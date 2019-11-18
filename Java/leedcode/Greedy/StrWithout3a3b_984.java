package Greedy;

/**
 * 984. 不含 AAA 或 BBB 的字符串
 * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 * **/

public class StrWithout3a3b_984 {
    public String strWithout3a3b(int A, int B) {
        if (A == 0 && B == 0)
        	return null;
        String result = "";
        if (A == B) {
        	while(A > 0) {
        		result += "ab";
        		A--;
        	}
        }
        else if (A > B) {
        	while (A > B && A > 0) {
        		if (B > 0) {
        			result += "aab";
        			A -= 2;
        			B--;
        		}
        		else {
        			result += "A";
        			A--;
        		}
        	}
        	while (A <= B && A > 0) {
        		result += "ab";
        		A--;
        		B--;
        	}
        }
        else {
        	while (B > A && B > 0) {
        		if (A > 0) {
            		result += "bba";
            		B -= 2;
            		A--;
        		}
        		else {
        			result += "b";
        			B--;
        		}

        	}
        	while (B <= A && B > 0) {
        		result += "ba";
        		B--;
        		A--;
        	}
        }
        return result;
    }
}
