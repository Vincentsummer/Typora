package Greedy;

/**
 * 860. 柠檬水找零
 * 
 * **/

public class LemonadeChange_860 {
	// 贪心：零钱中有10元时尽可能多地替换10元
    public boolean lemonadeChange(int[] bills) {
        int ten = 0, five = 0;
        for (int bill : bills) {
        	if (bill == 5)
        		five++;
        	else if (bill == 10) {
        		if (five == 0)
        			return false;
        		ten++;
        		five--;
        	}
        	else if (bill == 20) {
        		if (ten > 0 && five > 0) {
        			five--;
        			ten--;
        		}
        		else if (five >= 3)
        			five -= 3;
        		else 
        			return false;
        	}
        }
        return true;
    }
}
