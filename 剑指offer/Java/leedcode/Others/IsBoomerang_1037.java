package Others;

public class IsBoomerang_1037 {
	// 思路一 ： 已知三个顶点坐标来计算三角形面积是否为0
	// 面积公式S=1/2[(x1y2-x2y1)+(x2y3-x3y2)+(x3y1-x1y3)],去个常数项，提个公因子。
    public boolean isBoomerang(int[][] p) {
        return (p[0][0] * (p[1][1] - p[2][1]) + p[1][0] * (p[2][1] - p[0][1]) + p[2][0] * (p[0][1] - p[1][1])) != 0;
    }
    
    // 思路二：判断斜率是否为0
    public boolean isBoomerang1(int[][] points) {
        double diffy1 = points[1][1] - points[0][1];
        double diffy2 = points[2][1] - points[1][1];
        double diffy3 = points[2][1] - points[0][1];
        double diffx1 = points[1][0] - points[0][0];
        double diffx2 = points[2][0] - points[1][0];
        double diffx3 = points[2][0] - points[0][0]; 
        if ((diffx1 == 0 && diffy1 == 0) || (diffx2 == 0 && diffy2 == 0)
            || (diffx3 == 0 && diffy3 == 0))
            return false;
        if (diffx1 == 0 && diffx2 == 0)
            return false;
        if (diffx1 == 0 || diffx2 == 0)
            return true;

        double res = Math.abs(diffy1 / diffx1 - diffy2 / diffx2);
        return !(res < 0.0000001);
    }
}
