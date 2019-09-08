package Others;

/**
 * 6. Z 字形变换
 * **/

public class convert_6 {
    public String convert(String s, int numRows) {
    	if (numRows == 1)
    		return s;
    	String str[] = new String[numRows];
    	for (int i = 0; i < numRows; ++i)
    		str[i] = "";
    	
    	boolean flag = false;
    	int j =0;
    	for (int i = 0; i < s.length(); ++i) {
    		str[j] += (s.charAt(i));
    		if (j == 0 || j == numRows - 1) flag = !flag;
    		j += flag ? 1 : -1;
    	}
    	String res = "";
    	for (int k = 0; k < numRows; ++k)
    		res += str[k];
    	return res;
    }
}
