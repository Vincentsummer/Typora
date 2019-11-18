package Greedy;

/**
 * 406. 根据身高重排队列
 * **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ReconstructQueue_406 {
	// 先将数组按照身高降序，K升序排序，然后按照K值重新插入
    public int[][] reconstructQueue(int[][] people) {
        if (0 == people.length || 0 == people[0].length)
            return new int[0][0];
        Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[0] == arg1[0] ? arg0[1] - arg1[1] : arg1[0] - arg0[0];
			}
		});
        List<int[]> result = new ArrayList<>();
        //K值定义为 排在h前面且身高大于或等于h的人数 
        //因为从身高降序开始插入，此时所有人身高都大于等于h
        //因此K值即为需要插入的位置
        for (int[] i : people) 
        	result.add(i[1], i);
        return result.toArray(new int[result.size()][]);
    }
}
