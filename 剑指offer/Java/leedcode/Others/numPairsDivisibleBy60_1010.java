package Others;
/**
 * 1010. 总持续时间可被 60 整除的歌曲
 * **/

// 用一个数组记录每个时间对应的模60结果的总和，如果两个数的模相加为60，则这两个数相加是60的倍数
public class numPairsDivisibleBy60_1010 {
    public int numPairsDivisibleBy60(int[] time) {
        int[] record = new int[60];
        int count = 0;
        for(int t : time){
            t %= 60;        //求这个时间的余数
            if(t != 0)  
                count += record[60 - t];    //如果时间余数不为0，找出相加为0的余数总和相加
            else count += record[t];        //如果为0，加其他为0的数
            record[t] ++;
        }
        return count;
    }
}
