package Others;
/**
 * 66. 加一
 * **/
public class plusOne_66 {
	public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 0;
        for (int i = n - 1; i >= 0; --i) {
        	if (digits[i] + carry == 10) {
        		digits[i] = 0;
        		carry = 1;
        	}
        	else {
        		digits[i] += 1;
        		carry = 0;
        		break;
        	}
        }
        if (carry == 0)
        	return digits;
        else {
        	int res[] = new int[n+1];
        	res[0] = 1;
        	return res;
        }
    }
}
