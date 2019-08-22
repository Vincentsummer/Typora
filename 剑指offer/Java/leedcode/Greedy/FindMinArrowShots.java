package Greedy;

/**
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束
 * 坐标。平面内最多存在104个气球。
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们
 * 想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * **/

import java.util.Arrays;
import java.util.Comparator;

public class FindMinArrowShots {
	
	// 贪心算法：将数组先根据结束点排序，再根据开始点排序，然后遍历，记录不重叠区间的个数即可。
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0 || points[0].length == 0)
        	return 0;
        
        Arrays.sort(points, new Comparator<int[]>() {
			@Override
			public int compare(int[] arg0, int[] arg1) {
				return arg0[1] == arg1[1] ? arg0[0] - arg1[0] : arg0[1] - arg1[1];
			}
		});
        
        int result = 1, pre = 0;
        for (int i = 0; i < points.length; ++i) {
        	if (points[i][0] > points[pre][1]) {
        		result++;
        		pre = i;
        	}
        }
        return result;
    }
}
