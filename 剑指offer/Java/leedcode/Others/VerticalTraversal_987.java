package Others;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 987. 二叉树的垂序遍历
 * **/
public class VerticalTraversal_987 {
	public List<List<Integer>> verticalTraversal(Tree root) {
        List<Pos> pos = new ArrayList<>();
        preorderTraversal(root, pos, new Pos(0, 0));
        pos.sort(new Comparator<Pos>() {	
			public int compare(Pos o1, Pos o2) {
				if (o1.X == o2.X)
					return o1.Y == o2.Y ? o1.val - o2.val : o2.Y - o1.Y;
				return o1.X - o2.X;
			}
		});
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int x = pos.get(0).X;
        for (Pos p : pos) {
        	System.out.println("X: " + p.X + "Y: " + p.Y + "val: " + p.val);
        	if (tmp.isEmpty() || p.X == x)
        		tmp.add(p.val);
        	else {
        		res.add(new ArrayList<>(tmp));
        		tmp.clear();
        		tmp.add(p.val);
        		x = p.X;
        	}	
        }
        res.add(new ArrayList<>(tmp));
        return res;
    }
	
    public void preorderTraversal(Tree root, List<Pos> pos, Pos p) {
    	if (root == null) return;
    	p.val = root.val;
    	pos.add(p); 
    	preorderTraversal(root.left, pos, new Pos(p.X-1, p.Y-1));
    	preorderTraversal(root.right, pos, new Pos(p.X+1, p.Y-1));
    }
}

class Pos{
	int X = 0;
	int Y = 0;
	int val = 0;
	Pos(int a, int b){X = a; Y = b;}
}

class Tree{
	int val;
	Tree left;
	Tree right;
	Tree(int x) {val = x;}
}
