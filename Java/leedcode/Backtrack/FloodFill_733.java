package Backtrack;

/**
 * 733. 图像渲染
 * **/

public class FloodFill_733 {
    // 回溯 DFS
    public int[][] floodFill1(int[][] image, int sr, int sc, int newColor){
    	int color = image[sr][sc];
    	fill(image, sr, sc, color, newColor);
    	return image;
    }
    
    public void fill(int[][] image, int x, int y, int color, int newColor) {
        // 出界：超出数组边界
        if (!inArea(image, x, y)) return;
        // 碰壁：遇到其他颜色，超出 origColor 区域
        if (image[x][y] != color) return;
        // 已探索过的 origColor 区域
        if (image[x][y] == -1) return;
        
        // choose：打标记，以免重复
        image[x][y] = -1;
        fill(image, x, y + 1, color, newColor);
        fill(image, x, y - 1, color, newColor);
        fill(image, x - 1, y, color, newColor);
        fill(image, x + 1, y, color, newColor);
        // unchoose：将标记替换为 newColor
        image[x][y] = newColor;
    }
    
    public boolean inArea(int[][] image, int x, int y) {
        return x >= 0 && x < image.length
            && y >= 0 && y < image[0].length;
    }
}
