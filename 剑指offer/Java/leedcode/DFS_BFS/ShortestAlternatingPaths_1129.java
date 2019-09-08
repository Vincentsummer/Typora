package DFS_BFS;

/**
 * 1129. 颜色交替的最短路径
 * **/

import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestAlternatingPaths_1129 {
	
	/**
	 * BFS或者DFS均可。
	 * 本题有两个关键点需要注意，一是颜色交替，意味着上一步的路径颜色与下一步的路径颜色一定不相同，
	 * 二题中明确说明了，图中可能存在环，或者不同颜色的平行边（edge：blue:（0，1）,red(0,1)）为两条
	 * 不同的边。颜色交替的解决办法，我们每次传递时，将上一次遍历的边的颜色信息带上，在下一次遍历时，
	 * 选择不同的颜色的邻接节点,节点信息为（node,color)。由于存在环和平行边，我们用数组visit[x][y][color] = true,
	 * 代表从节点x到节点y的且颜色为color的边被访问过，防止重复访问。
	 * **/
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        int res[] = new int[n];
        boolean color[][][] = new boolean[n][n][2]; 	
        Queue<Point> aux = new LinkedList<>();
        Arrays.fill(res, Integer.MAX_VALUE);
        aux.offer(new Point(0, 0));
        aux.offer(new Point(0, 1)); 	// red : 1, blue : 0
        int step = 0;
        while (!aux.isEmpty()) {
        	step++;
        	int sz = aux.size();
        	for (int i = 0; i < sz; ++i) {
            	Point p = aux.poll();
            	if (p.y == 0) {
            		for (int[] re : red_edges) {
            			if (re[0] == p.x && !color[re[0]][re[1]][1]) {
            				res[re[1]] = Math.min(res[re[1]], step);
            				aux.offer(new Point(re[1], 1));
            				color[re[0]][re[1]][1] = true;
            			}
            		}
            	}
            	else {
    				for (int[] bl : blue_edges) {
            			if (bl[0] == p.x && !color[bl[0]][bl[1]][0]) {
            				res[bl[1]] = Math.min(res[bl[1]], step);
            				aux.offer(new Point(bl[1], 0));
            				color[bl[0]][bl[1]][0] = true;
            			}
    				}
    			}
            }
        }
        res[0] = 0;
        for (int i = 0; i < n; ++i) {
        	if (res[i] == Integer.MAX_VALUE)
        		res[i] = -1;
        }
        return res;
    }
}
