package Others;

/**
 * 831. 隐藏个人信息
 * **/

public class MaskPII_831 {
    public String maskPII(String S) {
        String res = "";

        if (S.contains("@")) {
        	res += String.valueOf(S.charAt(0)).toLowerCase();
        	res += "*****";
        	for (int i = S.indexOf('@')-1; i < S.length(); ++i)
        		res += String.valueOf(S.charAt(i)).toLowerCase();
        }
        else {
        	String num = "";
        	for (int i = 0; i < S.length(); ++i) {
        		if (Character.isDigit(S.charAt(i)))
        			num += S.charAt(i);
        	}
        	if (num.length() > 10) {
        		res += "+";
        		for (int i = 0; i <= num.length() - 9; ++i)
        			res += "*";
        		res += "-";
        	}
        	res += "***-***-" + num.substring(num.length() - 4);
        }
        return res;
    }
}
