package Others;

import java.util.HashSet;
import java.util.Set;

/**
 * 824. 山羊拉丁文
 * **/

public class ToGoatLatin_824 {
    public String toGoatLatin(String S) {
        String str[] = S.split(" ");
        String goat = "maa";
        String res = "";
        char tmp[] = {'a', 'e','i','o','u','A','E','I','O','U'};
        Set<Character> dict = new HashSet<>();
        for (int i = 0; i < tmp.length; ++i)
            dict.add(tmp[i]);
        for (int i = 0; i < str.length; ++i){
            if (!dict.contains(str[i].charAt(0))){
                str[i] = str[i].substring(1) + str[i].charAt(0) + goat;
            }
            else
                str[i] += goat;
            goat += "a";
            res += i == str.length - 1 ? str[i] : str[i] + " ";
        }
        return res;
    }
}
