package Others;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection_394 {
	// 使用Set集合
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> aux = new HashSet<>();
        int res[] = new int[nums2.length];
        for (int i = 0; i < nums2.length; ++i)
            aux.add(nums2[i]);
        int j = 0;
        for (int i = 0; i < nums1.length; ++i){
            if (aux.contains(nums1[i])){
                res[j++] = nums1[i];
                aux.remove(nums1[i]);
            }
        }
        return Arrays.copyOfRange(res, 0, j);
    }
}
