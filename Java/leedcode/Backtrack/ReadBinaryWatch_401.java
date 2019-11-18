package Backtrack;

/**
 * 401. 二进制手表
 * **/

import java.util.ArrayList;
import java.util.List;

public class ReadBinaryWatch_401 {
	public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int led[] = new int[10];
        Backtrack(num, 0, res, led);
        return res;
    }
    
    public void Backtrack(int num, int t, List<String> res, int[] led){
        if (t >= 10){
            if (num > 0)
                return;
            int hours = 0, minute = 0;
            for (int i = 0; i < 4; ++i)
                hours += led[i] * (1 << (3 - i));
            for (int i = 4; i < 10; ++i)
                minute += led[i] * (1 << (9 - i));
            if (hours <= 11 && minute <= 59){
                String s = String.valueOf(hours) + ":";
                s += minute < 10 ? "0" + String.valueOf(minute) : String.valueOf(minute);
                res.add(s);
            }
            return;
        }
        if (num > 0){
            led[t] = 1;
            Backtrack(num-1, t+1, res, led);
        }          
        led[t] = 0;
        Backtrack(num, t+1, res, led); 
    }           
}
