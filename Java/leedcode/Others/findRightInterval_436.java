package Others;
import java.util.TreeMap;

/**
 * 436. 寻找右区间
 * **/

public class findRightInterval_436 {
	// TreeMap
	 public int[] findRightInterval(int[][] intervals) {
			int[] arr={-1};
			if(intervals.length <= 1) return arr;
			
			TreeMap<Integer,Integer> map=new TreeMap<>();
			for(int i=0; i<intervals.length; i++)
				map.put(intervals[i][0], i);
			
			arr=new int[intervals.length];
			for(int j=0; j<intervals.length; j++){
				Integer key = map.higherKey(intervals[j][1]-1);
				if(key==null) 
					arr[j] = -1;
				else 
					arr[j] = map.get(key);
			}	
	        return arr;
	    }
}
