package Others;

/**
 * 1046. 最后一块石头的重量
 * **/

import java.util.Comparator;
import java.util.PriorityQueue;

public class lastStoneWeight_1046 {
    public int lastStoneWeight(int[] stones) {
    	// 使用优先级队列
        PriorityQueue<Integer> stoneQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0 > arg1 ? 1 : -1;
			}
		});
        for (int i = 0; i < stones.length; ++i)
        	stoneQueue.offer(stones[i]);
        
        while(stoneQueue.size() > 1) {
        	int y = stoneQueue.poll();
        	int x = stoneQueue.poll();
        	if (x != y)
        		stoneQueue.offer(y-x);
        }
        
        if (!stoneQueue.isEmpty())
        	return stoneQueue.peek();
        return 0;
    }
}
