package Backtrack;
/**
 * 1079. 活字印刷
 * **/
public class NumTilePossibilities_1079 {
	/**
	 * 统计每个字母出现的频次，数目不定的排列树。
	 * **/
    public int numTilePossibilities(String tiles) {
        int count[] = new int[26];
        for (int i = 0; i < tiles.length(); ++i)
            count[tiles.charAt(i) - 'A']++;
        return backtrack(tiles, count);
    }

    public int backtrack(String tiles, int[] count){
        int res = 0;
        for (int i = 0; i < 26; ++i){
            if (count[i] == 0)
                continue;
            res += 1;
            count[i]--;
            res += backtrack(tiles, count);
            count[i]++;
        }
        return res;
    }
}
