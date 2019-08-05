### 1.随机变量 $X,Y$ 相互独立同分布，均服从正态分布 $N(\mu,\sigma^2)$ ,求 $\max(X,Y),\min(X,Y)$ 的数学期望

- **去 $\max$ 和 $\min$ 的符号**

$$
\max(X,Y)=\frac{1}{2}(X+Y+|X-Y|) \\
\min(X,Y)=\frac{1}{2}(X+Y-|X-Y|)
$$
- **设 $U=\frac{X-\mu}{\sigma},V=\frac{Y-\mu}{\sigma}$ ,则 $U,V$ 服从$N(0,1)$ 分布。**

	有 $X=\mu+\sigma U$,   $V=\mu+\sigma V$。因此：
	$$
	\max(X,Y)=\sigma \cdot \max(U,V)+\mu \\
	\min(X,Y)=\sigma \cdot \min(U,V)+\mu
	$$

- **问题转化为求解 $\max(U,V),\min(U,V)$。**

	​	$E(\max(X,Y))=E(\sigma \cdot \max(U,V)+\mu)=\sigma E(\max(U,V))+\mu$

	​	$E(\min(X,Y))=E(\sigma \cdot \min(U,V)+\mu)=\sigma E(\min(U,V))+\mu$

​	又有：

​		$\max(U,V)=\frac{1}{2}(U+V+|U-V|)$

​		$\min(U,V)=\frac{1}{2}(U+V-|U-V|)$

​	其中 $E(U)=0,E(V)=0$。

​    可以得到：

​		$E(\max(U,V))=\frac{1}{2}E(|U-V|)$

​		$E(\min(U,V))=\frac{1}{2}E(|U-V|)$

- **问题归约为求解 $E(|U-V|)$。**

	伽马函数：$\Gamma(x)=\int_0^{+\infty}t^{x-1}e^{-t}dt$

	令$t \to t^2$，有 $\Gamma(x)=2\int_0^{+\infty}t^{2x-1}e^{-t^2}dt$ ，且 $\Gamma(\frac{1}{2})=\sqrt\pi,\Gamma(1)=1,\Gamma(x)=x\Gamma(x)$

	令 $Z=U-V$，可知 $Z\sim N(0,2)$，$f_Z(z)=\frac{1}{2\sqrt\pi}e^{-\frac{z^2}{4}}$。
	$$
	\begin{align}
	E(|U-V|)=E(|Z|)&=\int_{-\infty}^{+\infty}|Z|f_Z(Z)dz \\
	&=2\int_0^{+\infty}zf_Z(z)dz=2\int_0^{+\infty}\frac{1}{2\sqrt\pi}e^{-\frac{z^2}{4}}dz \\
	&=\frac{1}{2\sqrt\pi}\cdot8\int_0^{+\infty}\frac{z}{2}e^{-(\frac{z}{2})^2}d\frac{z}{2} \\
	&=\frac{2}{\sqrt\pi}\cdot\Gamma(1)=\frac{2}{\sqrt\pi}
	\end{align}
	$$
	

	回代，有：

	$E(\max(X,Y))=\mu+\sigma\frac{1}{\sqrt\pi}$

	$E(\min(X,Y))=\mu-\sigma\frac{1}{\sqrt\pi}$

### 2.判断一个序列是否为合理的出栈顺序

- 使用一个队列和一个辅助栈来解决该问题，其中，order存储待判断的出栈序列，而s用于模拟序列中每个元素的入栈和出栈过程。

  首先按照入栈的顺序将每个元素压入辅助栈：

  ​        每次压入一个元素，检查栈顶元素与队列头元素是否相同，若相同，s与order同时执行pop操作。

  ​        若最终辅助栈为空，说明队列里存放的序列是合理的出栈顺序，否则就是不合理的出栈序列。

  ​        Java实现如下：

  ```java
  package test;
  
  import java.util.LinkedList;
  import java.util.Queue;
  import java.util.Stack;
  
  public class test {
  
  	public static void main(String[] args) {
  		int[]  inputData = new int[] {1,2,3,4,5},
  			     outputData = new int[] {4,5,3,2,1};
  		System.out.println(fun(inputData, outputData));
  	}
      
  	private static boolean fun(int[] inputData, int[] outputData) {
  		Stack<Integer> auxStack = new Stack<>();
  		Queue<Integer> outOrder = new LinkedList<>();
  		
  		for (int i = 0; i < outputData.length; i++)
  			outOrder.offer(outputData[i]);
  		
  		for (int i = 0; i < inputData.length; i++) {
  			auxStack.push(inputData[i]);
  			while (!auxStack.isEmpty() && auxStack.peek() == outOrder.peek()) {
  				auxStack.pop();
  				outOrder.poll();
  			}
  		}
  		if (auxStack.isEmpty())
  			return true;
  		else
  			return false;
  	}
  }
  ```


### 3.前缀树（字典树）

​	Trie，又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。

![1560500578434](assets/1560500578434.png)

前缀树基本性质：

（1）根节点不包含字符，除根节点意外每个节点只包含一个字符。

（2）从根节点到某一个节点，路径上经过的字符连接起来，为该节点对应的字符串。

（3）每个节点的所有子节点包含的字符串不相同。

应用：
	1、前缀匹配
	2、字符串检索
	3、词频统计
	4、字符串排序

```java
//	定义前缀树的结构
class Node{
	char 	content; // the character in the node
	boolean isEnd;   // whether the end of the words
	int 	count;   // the number of words sharing this character
	LinkedList<Node> childList;  // the child list
	
	public Node(char c) {
		childList = new LinkedList<>();
		isEnd     = false;
		content   = c;
		count     = 0;
	}

	public Node subNode(char c) {
		if (childList != null) {
			for (Node eachChild : childList) {
				if (eachChild.content == c)
					return eachChild;
			}
		}
		return null;
	}
}
```

Trie类的具体实现：

```java
class Trie {
	private Node root;
	/** Initialize your data structure here. */
    public Trie() {
    	root = new Node(' ');
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
    	if (search(word))
    		return;
    	Node current = root;
    	for (int i = 0; i < word.length(); i++) {
    		Node child = current.subNode(word.charAt(i));
    		if (child != null)
    			current = child;
    		else {
    			current.childList.add(new Node(word.charAt(i)));
    			current = current.subNode(word.charAt(i));
                }
            current.count++;
   	 	}
    
    	// Set isEnd to indicate end of the word
    	current.isEnd = true;
	}

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node current = root;

        for (int i = 0; i < word.length(); i++) {
            if (current.subNode(word.charAt(i)) == null)
                return false;
            else
                current = current.subNode(word.charAt(i));
        }

        if (current.isEnd == true)
            return true;
        else
            return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node current = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (current.subNode(prefix.charAt(i)) == null)
                return false;
            else
                current = current.subNode(prefix.charAt(i));
        }
        return true;
    }
     public void deleteWord(String word){
         if(search(word) == false) 
             return; 
         Node current = root; 
         for(char c : word.toCharArray()) { 
             Node child = current.subNode(c); 
             if(child.count == 1) { 
                 current.childList.remove(child); 
                 return; 
             } 
             else 
             { 
             	 child.count--;
              	 current = child; 
             } 
         }
         current.isEnd = false; 
     }
}
```



### 4.分割等和子集&划分为k个相等的子集

#### 分割等和子集

- 问题描述：给定一个**只包含正整数**的**非空**数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

  ```java
  <示例1>
  输入: [1, 5, 11, 5]
       
  输出: true
       
  解释: 数组可以分割成 [1, 5, 5] 和 [11].
  
  <示例2>
  输入: [1, 2, 3, 5]
   
  输出: false
   
  解释: 数组不能分割成两个元素和相等的子集.
  ```

- 题解：分割等和子集，可以先求和，再平分。如果不能平分，则直接返回false，否则，问题就变成了判断数组是否存在若干个元素求和正好等于数组和的一半，直接遍历所有的结果，使用深搜（DFS）解决。另外一种方法是将这个问题转化为背包问题，使背包的大小为数组和的一半，问题转化为是否存在数组元素使背包正好被装满，也就是最优解是数组和的一半。

  - DFS 解法

  ```java
  class Solution {
      public boolean canPartition(int[] nums) {
          int sum=0;
          for(int n:nums)
              sum+=n;
          if(sum%2!=0)
              return false;
          return subsetSum(nums,sum>>>1);
      }
      public boolean subsetSum(int[] nums, int s) {
          boolean[] dp = new boolean[s + 1]; 
          dp[0] = true;
          for (int n : nums){
              for (int i = s; i >= n; i--){
                  dp[i] = dp[i]||dp[i - n]; 
              }
              if(dp[s])
                  return true; 
          }
          return dp[s];
      } 
  }
  ```

  - 背包求解

  ```java
  class Solution {
      public boolean canPartition(int[] nums) {
          int n=nums.length;
          int sum=0;
          for(int i=0;i<n;i++){
              sum+=nums[i];
          }
          int t=sum/2;
          if(sum%2!=0)
              return false;
          int[] dp=new int[t+1];
          for(int i=0;i<n;i++){
              for(int j=t;j>=nums[i];j--){
                  dp[j]=Math.max(dp[j],dp[j-nums[i]]+nums[i]);
              }
          }
          if(dp[t]==t)
          	return true;
          return false;
      }
  }
  ```

#### 划分为k个相等的子集

- 问题描述：给定一个整数数组  `nums` 和一个正整数 `k`，找出是否有可能把这个数组分成 `k` 个非空子集，其总和都相等。

  ```java
  <示例1>
  输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
  输出： True
  说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
  ```

- 题解：与分割等和子集类似，将其中的等分变为多分即可。

  ```java
  class Solution {
      public boolean canPartitionKSubsets(int[] nums, int k) {
          int n=nums.length;
          int sum=0;
          for(int i=0;i<n;i++)
              sum+=nums[i];
          boolean[] v=new boolean[n];
          if(sum%k!=0)
              return false;
          return dfs(nums,k,sum/k,0,0,v);
      }
      public static boolean dfs(int[] nums,int k,int target,int sum,int s,
                                boolean[] v){
          if(k==1)
              return true;
          if(target<0)
              return false;
          if(target==sum)
              return dfs(nums,k-1,target,0,0,v);
          for(int i=s;i<nums.length;i++){
              if(v[i])
                  continue;
              v[i]=true;
              if(dfs(nums,k,target,sum+nums[i],i+1,v))
                  return true;
              v[i]=false;
          }
          return false;
      }
  }
  ```

  