# 剑指offer题解

## 面试题1：赋值运算符函数 

### 题目：如下为类型 CMyString 的声明，请为该类型添加赋值运算符函数。

```c++
class CMyString
{
public:
    CMyString(char* pData = nullptr);
    CMyString(const CMyString& str);
    ~CMyString(void);

private:
    char* m_pdata;
};
```

代码如下：

```c++
// 考虑如下4点：
// 1. 把返回值的类型声明为该类型的引用，并在函数结束前返回实例自身的引用
// 2. 把传入的参数的类型声明为常量引用。
// 3. 释放实例自身已有的内存。
// 4. 判断传入的参数和当前的实例（*this）是否为同一个实例。

// 经典解法
CMyString& CMyString::operator =(const CMyString &str)
{
    if (this == &str)
        return *this;

    delete []m_pdata;
    m_pdata = nullptr;

    m_pdata = new char[strlen(str.m_pdata) + 1];
    strcpy(m_pdata, str.m_pdata);

    return *this;
}

// 考虑异常安全性的解法
CMyString& CMyString::operator=(const CMyString &str)
{
    if (this != &str){
        CMyString strTemp(str);

        char* pTemp = strTemp.m_pdata;
        strTemp.m_pdata = m_pdata;
        m_pdata = pTemp;
    }

    return *this;
}
```



## 面试题2： 实现Singleton模式

### 题目：设计一个类， 我们只能生成该类的一个实例。



## 面试题3：数组中重复的数字

### 题目一：找出数组中重复的数字。

​	在一个长度为 $n$ 的数组里的所有数字都在 $0 \sim n-1$ 的范围内。数组中某些数字是重复的。请找出数组中任意一个重复的数字。

**解法：利用数组实现哈希表。时间复杂度 O(n)，空间复杂度 O(1)。**

C++代码：

```c++
// 测试用例
// 1. 数组中包含一个或多个重复的数字。{2，3，1，0，2，5，3}
// 2. 数组中不包含重复的数字。{1，2，3，4，5}
// 3. 无效输入用例（空指针，数组中包含0到n-1之外的数字）{}，{1，3，5，7}

bool duplicate(int numbers[], int length, int* duplication)
{
    // 空指针和无效长度判断
    if (numbers == nullptr || length <= 0)
        return false;

    // 无效数字判断 （数组中包含0到n-1之外的数字）
    for (int i = 0; i < length; ++i){
        if (numbers[i] < 0 ||numbers[i] > length - 1)
            return false;
    }

    // 遍历数组
    for (int i = 0; i < length; ++i){
        // 数组第i个位置的数字不为i
        while(numbers[i] != i){
            // 该情况表示存在重复数字numbers[i]
            if (numbers[i] == numbers[numbers[i]]){
                *duplication = numbers[i];
                return true;
            }
            // 否则交换两位置的数字
            int temp = numbers[i];
            numbers[i] = numbers[temp];
            numbers[temp] = temp;
        }
    }
    return false;
}
```

Java代码：

```java
// 无重复数字即无效输入时函数返回-1
public static int duplicate(int numbers[], int length) {
		if (numbers == null || length <= 0)
			return -1;
		
		for (int i = 0; i < length;  ++i) {
			if (numbers[i] < 0 || numbers[i] > length - 1)
				return -1;
		}
		
		for (int i = 0; i < length;  ++i) {
			while(numbers[i] != i) {
				if (numbers[i] == numbers[numbers[i]]) {
					int duplication = numbers[i];
					return duplication;
				}
				
				int temp = numbers[i];
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return -1;
	}
```

Python代码：

```

```

### 题目二：不修改数组找出重复的数字。

​	在一个长度为 $n+1$ 的数组里的所有数字都在 $1 \sim n$ 的范围内，所以数组中至少有一个数字是重复的。请找出数组中任意一个重复的数字，但不修改输入的数组。

**解法一：创建一个长度为 $n+1$ 的辅助数组，然后逐一把原数组的每个数字复制到辅助数组。如果原数组中被复制的数字是 $m$, 则把它复制到辅助数组中下标为 $m$ 的位置。时间复杂度O(n), 空间复杂度 O(n)。**

**解法二：二分查找，然后统计区间内数字的数目。将 $1 \sim n$ 从中间的数组 $m$ 分为两部分，若前一半区间内数字的数目超过 $m$, 则该区间存在重复的数字，否则后一半区间存在重复的数字。时间复杂度 O(nlogn)，空间复杂度 O(1)。<u>该算法不能保证找出所有重复的数字</u>。**

C++代码：

```

```

Java代码：

```java
/** 
* 测试用例:
* 1. 长度为n的数组里包含一个或多个重复的数字。
* 2. 数组中不包含重复的数字。
* 3. 无效输入测试用例（输入空指针）。
**/
// 递归解法
	public int getDuplicateWithRecursion(int[] numbers, int length, int start, int end) 	{
		if (numbers == null || length <=0 || start <= 0 || end <= 0)
			return -1;
		int count = getCount(numbers, length, start, end);
		if (start == end) {
			if (count > 1)
				return start;
			else
				return -1;
		}
		
		int middle = ((end - start) >> 1) + start;
		
		if (getCount(numbers, length, start, middle) > middle - start + 1)
			return getDuplicateWithRecursion(numbers, length, start, middle);
		else
			return getDuplicateWithRecursion(numbers, length, middle + 1, end);
	}

// 循环解法
	public int getDuplicate(int[] numbers, int length) {
		if (numbers == null || length <= 0)
			return -1;
		
		int start = 1, end = length - 1;
		while(start <= end) {
			int middle = ((end - start) >> 1) + start;
			int count = getCount(numbers, length, start, middle);
			if (end == start) {
				if (count > 1)
					return start;
				else
					break;
			}
			
			if (count > (middle - start + 1))
				end = middle;
			else
				start = middle + 1;
		}
		return -1;
	}
	
	public int getCount(int[] numbers, int length, int start,  int end) {
		
		if (numbers == null)
			return 0;
		
		int result = 0;
		for (int i = 0; i < length; ++i) {
			if (numbers[i] >= start && numbers[i] <= end)
				result++;
		}
		return result;
	}

```



## 面试题4：二维数组中的查找 

### 题目：在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样一个二维数组和一个整数，判断数组中是否含有该整数。

**解法：从数组右上角开始查找，逐步缩小数组范围。**

C++代码：

```

```

Java代码：

```java
/**
 * 测试用例：
 * 1. 二维数组中包含查找的数字。
 * 2. 二维数字中没有查找的数字。
 * 3. 特殊输入测试（输入空指针）。
 * **/
	public boolean find(int[][] matrix, int number) {

		if (matrix != null && matrix.length > 0) {
			int rows = matrix[0].length, columns = matrix.length;
			int row = 0, column = columns - 1;
			while (row < rows && column >= 0) {
				if (matrix[row][column] == number) {
					return true;
				}
				else if (matrix[row][column] > number)
					column--;
				else
					row++;
			}
		}
		return false;
	}
}
```



## 面试题5：替换空格

### 题目：请实现一个函数，把字符串中的每个空格替换成 ”%20“。（假设在原有字符串上进行替换，并且保证输入的字符串后有足够多的空余内存）

**解法一：从头到尾扫描字符串，每次碰到空格的时候进行替换，并把空格后的字符后移2字节。时间复杂度$O(n^2)$。**

**解法二：先遍历字符串，统计出字符串中空格的总数，由此计算出替换之后字符串的总长度。然后从后往前遍历。建立两个指针，P1和P2。P1指向原始字符串的末尾，P2指向替换之后的字符串末尾。** 

C++代码：

```c++
#include <iostream>

using namespace std;

void replaceBlank(char str[], int length) {
	if (str == NULL || length <= 0)
		return;
	int count = 0;

	// 统计字符串中空格的个数
	for (int i = 0; i < length; i++){
		if (str[i] == ' ')
			count ++;
	}

	// 两个指针，一个指向原始字符串末尾，一个指向替换后字符串的末尾。
	int indexOfOriginal = length - 1;
	int indexOfNew = indexOfOriginal + count * 2;

	// 从后往前遍历
	while (indexOfOriginal > 0 && indexOfNew > indexOfOriginal){
		// 碰到空格则替换
		if (str[indexOfOriginal] == ' '){
			str[indexOfNew --] = '0';
			str[indexOfNew --] = '2';
			str[indexOfNew --] = '%';
		}
		// 否则将原字符替换到indexOfNew的位置
		else{
			str[indexOfNew --] = str[indexOfOriginal];
		}

		-- indexOfOriginal;
	}
}
```

Java代码：

```

```

### 相关题目：有两个排序数组A1和A2，内存在A1的末尾有足够多的空余空间容纳A2，请实现一个函数，把A2中的所有数字插入A1中，并且所有的数字都是排序的。

​	解法同上。

​	**总结 ：**在合并两个数组（包括字符串）时，如果从前往后复制每个数字（或字符）则需要重复移动数字（或字符）多次，那么我们可以考虑从后往前复制，这样就能减少移动的次数，从而提高效率。



## 面试题6：从尾到头打印链表 

题目：输入一个链表的头节点，从尾到头反过来打印出每个节点的值。链表节点定义如下：

```c++
typedef struct ListNode
{
  int m_nKey;
  ListNode* m_pNext;
}LNode, *LinkList;
```

**解法："后进先出"，用栈或递归实现。**

C++代码

```c++
/**
 * 测试用例;
 * 1. 功能测试：输入的链表有多个节点或一个节点。
 * 2. 特殊输入测试：输入的链表头节点指针为nullptr。
 * **/

#include <iostream>
#include <stack>

// 栈实现
void printListReversingly_Stack(LinkList pHead){
	std::stack<LinkList> nodes;

	LinkList pNode = pHead;
	while (pNode != nullptr){
		nodes.push(pNode);
		pNode = pNode->m_pNext;
	}

	while (!nodes.empty()){
		pNode = nodes.top();
		printf("%d ", pNode->m_nValue);
		nodes.pop();
	}
}

// 递归实现
void printListReversingly_Recursively(LinkList pHead){
	if (pHead != nullptr){
		if (pHead->m_pNext != nullptr)
			printListReversingly_Recursively(pHead->m_pNext);
		printf("%d ", pHead->m_nValue);
	}
}

```

## 面试题7：重建二叉树

题目：输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的结果中都不含重复的数字。二叉树节点的定义如下：

```c++
typedef struct BinaryTreeNode{
	int 			m_nValue;
	BinaryTreeNode* m_pLeft;
	BinaryTreeNode* m_pRight;
}BiTNode, *BiTree;
```

**解法：递归寻找根结点及子树的根结点。**

C++代码：

```c++
/**
 * 测试用例：
 * 普通二叉树（完全二叉树，不完全二叉树）
 * 特殊二叉树（所有节点无右/左节点；只有一个节点）
 * 特殊输入测试（二叉树根结点指针为nullptr, 输入的前序遍历和后序遍历不匹配）
 * **/

BiTree Construct(int* preorder, int* inorder, int length){
	if (preorder == nullptr || inorder == nullptr || length <= 0)
		return nullptr;

	return ConstructCore(preorder, preorder + length - 1, inorder, inorder + length - 1);
}

BiTree ConstructCore(int* startPreorder, int* endPreorder, int* startInorder, int* endInorder){
	// 前序遍历序列的第一个数字是根结点的值
	int rootValue = startPreorder[0];
	BiTree root = new BiTNode();
	root->m_nValue = rootValue;
	root->m_pLeft = nullptr;
	root->m_pRight = nullptr;

	// 二叉树只含有一个节点
	if (startPreorder == endPreorder){
		if (startInorder == endInorder && *startPreorder == *startInorder)
			return root;
		else
			throw std::exception("Invalid input.");
	}

	// 在中序遍历中寻找根结点的值
	int* rootInorder = startInorder;
	while (rootInorder <= endInorder && *rootInorder != rootValue)
		++rootInorder;

	if (rootInorder == endInorder && *rootInorder != rootValue)
		throw std::exception("Invalid input.");

	int leftLength = rootInorder - startInorder;
	int* leftPreorderEnd = startPreorder + leftLength;
	if (leftLength > 0){
		// 构建左子树
		root->m_pLeft = ConstructCore(startPreorder + 1, leftPreorderEnd, startInorder, rootInorder);
	}
	if (leftLength < endPreorder - startPreorder){
		// 构建右子树
		root->m_pRight = ConstructCore(leftPreorderEnd+1, endPreorder, rootInorder+1, endInorder);
	}

	return root;
}
```



## 面试题8：二叉树的下一个节点

### 题目：给定一棵二叉树和其中的一个节点，找出其中序遍历序列的下一个节点。树中的节点除了有两个分别指向左、右节点的指针，还有一个指向父节点的指针。

C++代码：

```c++
/**
 * 测试用例：
 * 1. 普通二叉树（完全二叉树，不完全二叉树）
 * 2. 特殊二叉树（代码中的几种情况）
 * 3. 不同位置的节点的下一个节点
 * **/

TNode GetNext(TNode pNode){
	if (pNode == nullptr)
		return nullptr;

	TNode pNext = nullptr;
	// 若该节点有右子树，则next为其右子树的最左子节点
	if (pNode->right != nullptr){
		TNode pRight = pNode->right;
		while(pRight->left != nullptr)
			pRight = pRight->left;

		pNext = pRight;
	}

	// 若该节点无右子树且有父节点，分两种情况
	// 1. 若其为父节点的左子节点，则next为父节点
	// 2. 若其为父节点的右子节点，则沿着指向父节点的指针向上遍历，直到找到一个是它父节点的左子节点的节点。
	// 则该节点的父节点即为next。
	else if (pNode->parent != nullptr){
		TNode pCurrent = pNode;
		TNode pParent = pNode->parent;
		while (pParent != nullptr && pCurrent == pParent->right){
			pCurrent = pParent;
			pParent = pParent->parent;
		}
		pNext = pParent;
	}

	return pNext;
}
```



## 面试题9：用两个栈实现队列

### 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和 deleteHead，分别完成在队列尾部插入节点和在队列头部删除节点的功能。

C++代码：

```c++
/**
 * 测试用例：
 * 1. 往空的队列里添加、删除元素。
 * 2. 往非空的队列里添加、删除元素。
 * 3. 连续删除元素直至队列为空。
 * **/

template<class T>
void CQueue<T>::appendTail(const T& element){
	stack1.push(element);
}

template<class T>
T CQueue<T>::deleteHead(){
	if (stack2.size() <= 0){
		while(stack1.size() > 0){
			T& data = stack1.top();
			stack1.pop();
			stack2.push(data);
		}
	}

	if (stack2.size() == 0)
		throw "queue is empty!";

	T head = stack2.top();
	stack2.pop();

	return head;
}
```

### 相关题目：用两个队列实现一个栈。



## 面试题10：斐波那契数列 

### 题目一：求斐波那契数列的第 $n$ 项。

写一个函数，输入 $n$ ，求斐波那契数列的第 $n$ 项。斐波那契数列的定义如下：
$$
f(n)=
\begin{cases}
0 \qquad\qquad\qquad\qquad\qquad n=0 \\
1 \qquad\qquad\qquad\qquad\qquad n =1 \\
f(n-1)+f(n-2) \qquad n > 1
\end{cases}
$$
C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试
 * 2. 边界值测试
 * 3. 性能测试（输入较大的数字）
 * **/

// 递归解法 时间复杂度 O(n!)
long long Fibonacci_Recursive(unsigned int n){
	if (n <= 0)
		return 0;
	if (n == 1)
		return 1;

	return Fibonacci_Recursive(n-1) + Fibonacci_Recursive(n-2);
}

// 从下往上计算，动态规划思想, 时间复杂度 O(n)
long long Fibonacci(unsigned n){
	int result[2] = {0, 1};
	if (n < 2)
		return result[n];

	long long fibNMinusOne = 1;
	long long fibNMinusTwo = 0;
	long long fibN = 0;
	for (unsigned int i = 2; i <= n; i++){
		fibN = fibNMinusOne + fibNMinusTwo;
		fibNMinusTwo = fibNMinusOne;
		fibNMinusOne = fibN;
	}
	return fibN;
}
```

### 题目二：青蛙跳台阶问题。

一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上一个 $n$ 级台阶总共有多少跳法。

**解法：同斐波那契数列。**



## 面试题11：旋转数组的最小数字

### 题目：把一个数组最开始的若干个元素搬到数组的末尾，称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素，例如，数组 {3,4,5,1,2} 为 {1,2,3,4,5} 的一个旋转，该数组的最小值为1。

**解法：二分查找，需考虑有重复数字的情况，特殊情况只能顺序查找。**

```c++
/**
 * 测试用例：
 * 1. 功能测试
 * 2. 边界值测试
 * 3. 特殊输入测试
 * **/

int MinInOrder(int* numbers, int index1, int index2){
	int result = numbers[index1];
	for (int i = index1 + 1; i <= index2; ++i){
		if (result > numbers[i])
			result = numbers[i];
	}
	return result;
}

int Min(int* numbers, int length){
	if (numbers == nullptr || length <= 0)
		throw "Invalid parameters";
	int index1 = 0, index2 = length - 1, indexMid = index1;

	// 二分查找
	while(numbers[index1] >= numbers[index2]){
		// 当两指针相隔为1时 break
		if (index2 - index1 == 1){
			indexMid = index2;
			break;
		}

		indexMid = (index1 + index2) / 2;

		// 当三者指向的数字都相等时，只能顺序查找
		if (numbers[index1] == numbers[index2] && numbers[indexMid] == numbers[index1])
			return MinInOrder(numbers, index1, index2);

		if (numbers[indexMid] >= numbers[index1])
			index1 = indexMid;
		else if (numbers[indexMid] <= numbers[index2])
			index2 = indexMid;
	}
	return numbers[indexMid];
}
```

## 面试题12：矩阵中的路径

### 题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。

**解法：回朔法**

C++代码：

```C++
/**
 * 测试用例：
 * 1. 功能测试（在多行多列的矩阵中不存在路径）
 * 2. 边界值测试 （矩阵中只有一行或者一列，矩阵和路径中的所有字母都是相同的）
 * 3. 特殊输入测试 (输入nullptr指针)
 * **/

bool hasPathCore(const char* matrix, int rows, int cols, int row, int col, const char* str, int& pathLength, bool* visited){
	if (str[pathLength] == '\0')
		return true;

	bool hasPath = false;

	if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row * cols + col] == str[pathLength]
				&& !visited[row * cols + col]){
		++pathLength;
		visited[row * cols + col] = true;

		hasPath = hasPathCore(matrix, rows, cols, row, col-1, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row-1, col, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row, col+1, str, pathLength, visited)
				|| hasPathCore(matrix, rows, cols, row+1, col, str, pathLength, visited);

		if (!hasPath){
			--pathLength;
			visited[row * cols + col] = false;
		}
	}

	return hasPath;
}

bool hasPath(char* matrix, int rows, int cols, char* str){
	if (matrix == nullptr || rows < 1 || cols < 1 || str == nullptr)
		return false;
	bool* visited = new bool[rows * cols];

	// 函数原型是：void *memset(void *s, int ch, size_t n);
	// 函数功能是：将s所指向的某一块内存中的前n个字节的内容全部设置为ch指定的ASCII值。
	memset(visited, 0, rows * cols);

	int pathLength = 0;
	for (int row = 0; row < rows; ++row){
		for (int col = 0; col < cols; ++col){
			if (hasPathCore(matrix, rows, cols, row, col, str, pathLength, visited))
				return true;
		}
	}
	delete[] visited;
	return false;
}
```

## 面试题13：机器人的移动路径

### 题目：地上有一个 $m$ 行 $n$ 列的方格。一个机器人从坐标（0,0）的格子开始移动，它每次可以向左、右、上、下移动一格，但不能进入行坐标和列坐标的数位之和大于 $k$ 的格子。

**解法：同12题，回朔法**

C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试（方格为多行多列，k为正数）。
 * 2. 边界值测试（方格只有 一行或者只有一列，k等于0）
 * 3. 特殊输入测试（k为负数）
 * **/

int getDigitSum(int number){
	int sum = 0;
	while(number > 0){
		sum += number % 10;
		number /= 10;
	}
	return sum;
}

bool check(int threashold, int rows, int cols, int row, int col, bool* visited){
	if (row >= 0 && row < rows && col >= 0 && col < cols &&
			getDigitSum(row) + getDigitSum(col) <= threashold &&
			!visited[row * cols + col])
		return true;

	return false;
}

int movingCountCore(int threashold, int rows, int cols, int row, int col, bool* visited){
	int count = 0;
	if (check(threashold, rows, cols, row, col, visited)){
		visited[rows * cols + col] = true;

		count = 1 + movingCountCore(threashold, rows, cols, row-1, col, visited)
				+ movingCountCore(threashold, rows, cols, row, col-1, visited)
				+ movingCountCore(threashold, rows, cols, row, col+1, visited)
				+ movingCountCore(threashold, rows, cols, row+1, col, visited);
	}
	return count;
}

int movingCount(int threashold, int rows, int cols){
	if (threashold < 0 || rows < 0 || cols < 0)
		return 0;

	bool* visited = new bool[rows * cols];
	memset(visited, 0, rows * cols);

	int count = movingCountCore(threashold, rows, cols, 0, 0, visited);

	delete[] visited;
	return count;
}
```

**总结：通常在二维矩阵上寻找路径这类问题都可以通过回朔法来解决。**

## 面试题14：剪绳子

### 题目：有一根长度为 $n$ 的绳子，请把绳子剪成 $m$ 段（$m$、$n$ 都是整数，$n>1$ 并且 $m>1$ ），每段绳子的长度记为 $k[0],k[1],...,k[m]$。请问 $k[0] \times k[1] \times...\times k[m]$ 可能的最大乘积是多少？

**解法一：动态规划，时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。**

定义函数 $f(n)$ 为把长度为 $n$ 的绳子剪成若干段后各段长度乘积的最大值。在剪第一刀的时候，有 $n-1$ 种可能的选择，即第一段绳子的长度可能是 $1,2,...,n-1$，因此 $f(n)=\max(f(i) \times f(n-i))$，其中 $0<i<n$。

**解法二：贪心算法，时间和空间复杂度  $O(1)$**

当 $n\ge5$ 时，尽可能多地剪长度为 3 的绳子（可以证明这种情况下有：$2(n-2)>n$ 并且 $3(n-3)>n$，另外有：$3(n-3)\ge 2(n-2)$。）；当剩下的绳子长度为 4 时，把绳子剪成两段为 2 的绳子。

C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试（绳子的初始长度大于5）
 * 2. 边界值测试（绳子的初始长度小于5）
 * **/

// 动态规划方法
int maxProductAfterCutting_1(int length){
	if (length < 2)
		return 0;
	if (length == 2)
		return 1;
	if (length == 3)
		return 2;
    // 数组第i个元素表示把长度为i的绳子剪掉后的各段的最大乘积。
	int* products = new int[length+1];
	// 第i个元素为下标加1
	products[0] = 0;
	products[1] = 1;
	products[2] = 2;
	products[3] = 3;

	int max = 0;
	for (int i = 4; i <= length; ++i){
		max = 0;
		for (int j = 1; j <= i / 2; ++j){
			// 长度为i的绳子在剪第一次的时候有i-1种可能 其中最优值为 max(products[i] * products[i-j])
			int product = products[j] * products[i-j];
			if (max < product)
				max = product;
			products[i] = max;
		}
	}

	max = products[length];
	delete[] products;

	return max;
}

//贪心算法
int maxProductAfterCutting_2(int length){
	if (length < 2)
		return 0;
	if (length == 2)
		return 1;
	if (length == 3)
		return 2;

	// 尽可能多地剪去长度为 3 的绳子段
	int timesOf3 = length / 3;

	//当绳子最后剩下的长度为 4 的时候，不能再剪去长度为 3 的绳子段。 此时剪成 2X2
	if (length - timesOf3 * 3 == 1)
		timesOf3 -= 1;

	int timesOf2 = (length - timesOf3 * 3) / 2;
	return (int)(pow(3, timesOf3)) * (int)(pow(2, timesOf2));
}
```

## 面试题15：二进制中1的个数

### 题目：请实现一个函数，输入一个整数，输出该整数的二进制表示中1的个数。

**解法：位运算。**

C++代码：

```c++
/**
 * 测试用例：
 * 1. 正数（包括边界值1,0x7FFFFFFF）。
 * 2. 负数（包括边界值0x80000000,0xFFFFFFFF）。
 * **/

// 方式一:将数n与1做与运算，接着将1左移后不断重复上述过程
int NumberOf1_1(int n){
	int count = 0;
	unsigned int flag = 1;
	while(flag){
		if (n & flag)
			count++;

		flag = flag << 1;
	}

	return count;
}

// 方式二：把一个整数减去1后再和该整数做与运算，会把该整数最右边的1变成0。

int NumberOf1_2(int n){
	int count = 0;
	while (n){
		n &= (n-1);
		count++;
	}
	return count;
}
```

## 面试题16：数值的整数次方

### 题目：实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，不需要考虑大数问题。

**注意：考虑指数小于1（零和负数的情况）**

求整数次方的 $O(logn)$ 解法：
$$
a^n=
\begin{cases}
a^{n/2} \cdot a^{n/2} \qquad\qquad\qquad n \ 为偶数 \\
a^{(n-1)/2} \cdot a^{(n-1)/2} \cdot a \qquad n \ 为奇数
\end{cases}
$$
C++代码：

```c++
/**
 * 测试用例：
 * 将底数和指数分别设为正数、负数和零。
 * **/

bool g_InvalidInput = false;

// 两种求整数次方的方法
double PowerWithUnsignedExponent(double base, unsigned int exponent){
	double result = 1.0;
	for (unsigned int i = 1; i <= exponent; ++i)
		result *= base;

	return result;
}

// O(logn)解法
double PowerWithUnsignedExponent2(double base, unsigned int exponent){
	if (exponent == 0)
		return 1;
	if (exponent == 1)
		return base;

	double result = PowerWithUnsignedExponent2(base, exponent >> 1);
	result *= result;
	if (exponent & 0x01 == 1)
		result *= base;

	return result;
}

bool equal(double num1, double num2) {
	return (num1 - num2) > -0.0000001 && (num1 - num2) < 0.0000001 ? true : false;
}

// 分别讨论exponent不同情况
double Power(double base, int exponent){
	g_InvalidInput = false;

	if (equal(base, 0.0) && exponent < 0){
		g_InvalidInput = true;
		return 0.0;
	}

	unsigned int absExponent = (unsigned int)(exponent);
	if (exponent < 0)
		absExponent = (unsigned int)(-exponent);

	double result = PowerWithUnsignedExponent(base, absExponent);
	if (exponent < 0)
		result = 1.0 / result;

	return result;
}
```



## 面试题17：打印从1到最大的n位数

题目：输入数字 $n$，按顺序打印出从 $1$ 到 最大的 $n$ 位十进制数。

**解法：大数问题。用字符串数组表示大数，注意打印时需要去掉前置0。**

C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试（输入1、2、3...）
 * 2. 特殊输入测试（输入-1、0）
 * **/

// 判断number是否到达了最大的n位数。利用最高位的进位判断。
bool Increment(char* number){
	bool isOverflow = false;
	int nTakeOver = 0;
	int nLength = strlen(number);
	for (int i = nLength-1; i >= 0; i--){
		int nSum = number[i] - '0' + nTakeOver;
		if (i == nLength - 1)
			nSum++;
		if (nSum >= 10){
			if (i == 0)
				isOverflow = true;
			else{
				nSum -= 10;
				nTakeOver = 1;
				number[i] = '0' + nSum;
			}
		}
		else{
			number[i] = '0' + nSum;
			break;
		}
	}
	return isOverflow;
}

// 打印函数，打印时去掉前置0
void PrintNumber(char* number){
	bool isBegining0 = true;
	int nLength = strlen(number);

	for (int i = 0; i < nLength; ++i){
		if (isBegining0 && number[i] != '0')
			isBegining0 = false;
		if (!isBegining0)
			printf("%c", number[i]);
	}

	printf("\t");
}

void Print1ToMaxOfNDigits(int n){
	if (n <= 0)
		return;

	char* number = new char[n+1];
	memset(number, '0', n);
	number[n] = '\0';

	while(!Increment(number)){
		PrintNumber(number);
	}

	delete[] number;
}

/**
 * 递归法求解
 * **/

void Print1ToMaxOfDigitsRecursively(char* number, int length, int index){
	if (index == length - 1){
		PrintNumber(number);
		return;
	}

	for (int i = 0; i < 10; i++){
		number[index+1] = i + '0';
		Print1ToMaxOfDigitsRecursively(number, length, index+1);
	}
}

void Print1ToMaxOfNDigits2(int n){
	if (n <= 0)
		return;
	char* number = new char[n+1];
	number[n] = '\0';

	for (int i = 0; i < 10; i++){
		number[0] = i + '0';
		Print1ToMaxOfDigitsRecursively(number, n, 0);
	}

	delete[] number;
}
```

## 面试题18：删除链表的节点

### 题目一：在 $O(1)$ 时间内删除链表节点。

给定一个单向链表的头指针和一个节点指针，定义一个函数在 $O(1)$ 时间内删除该节点。链表节点与函数定义如下：

```c++
typedef struct ListNode{
	int 		m_nValue;
	ListNode* 	m_pNext;
}LNode, *LinkList;

void DeleteNode(ListNode* pListHead, ListNode* pTobeDeleted);
```

**思路：把下一节点的内容复制到待删除节点上覆盖原有的内容，再把下一个节点删除。这种思路基于一个前提：即待删除节点存在于链表中，而判断链表中是否包含某一节点需要 O(n) 的时间。**

C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试
 * 2. 特殊输入测试（指向链表头结点的为nullptr指针，指向待删除节点的为nullptr指针）
 * **/

/**O(1)时间内删除单链表中某一节点**/
// 此处可能对头结点指针进行修改，因此需要传递头结点指针的指针。
void DeleteNode(ListNode** pListHead, ListNode* pTobeDeleted){
	if (!pListHead || !pTobeDeleted)
		return;
	// 要删除的不是尾节点
	if (pTobeDeleted->m_pNext ！= nullptr){
		ListNode* pNext = pTobeDeleted->m_pNext;
		pTobeDeleted->m_nValue = pNext->m_nValue;
		pTobeDeleted->m_pNext = pNext->m_pNext;

		delete pNext;
		pNext = nullptr;
	}

	// 链表只有一个节点，删除头节点（也是尾节点）
	else if (*pListHead == pTobeDeleted){
		delete pTobeDeleted;
		pTobeDeleted = nullptr;
		*pListHead = nullptr;
	}

	// 链表中有多个节点，删除尾节点，此时仍需顺序遍历链表。
	else {
		ListNode* pNode = *pListHead;
		while(pNode->m_pNext != pTobeDeleted)
			pNode = pNode->m_pNext;

		pNode->m_pNext = nullptr;
		delete pTobeDeleted;
		pTobeDeleted = nullptr;
	}
}
```

### 题目二：删除链表中重复的节点

在一个排序的链表中删除重复的节点。

C++代码：

```c++
/**
 * 测试用例：
 * 1. 功能测试
 * 2. 特殊输入测试（指向头结点的指针为nullptr指针，链表中的所有节点都是重复的）
 * **/

void DeleteDuplication(ListNode** pHead){
	if (pHead == nullptr || *pHead == nullptr)
		return;
	// 指向当前节点的前一个节点的指针
	ListNode* pPreNode = nullptr;
	// 指向当前接节点的指针，注意此处的pHead为二级指针。
	ListNode* pNode = *pHead;
	while(pNode != nullptr){
		ListNode* pNext = pNode->m_pNext;

		// 判断当前节点是否为重复节点
		bool needDelete = false;
		if (pNext != nullptr && pNext->m_nValue == pNode->m_nValue)
			needDelete = true;

		if (!needDelete){
			pPreNode = pNode;
			pNode = pNode->m_pNext;
		}
		else{
			int value = pNode->m_nValue;
			ListNode* pToBeDel = pNode;
			while(pToBeDel != nullptr && pToBeDel->m_nValue == value){
				pNext = pToBeDel->m_pNext;

				delete pToBeDel;
				pToBeDel = nullptr;

				pToBeDel = pNext;
			}

			if (pPreNode == nullptr)
				*pHead = pNext;
			else
				pPreNode->m_pNext = pNext;
			pNode = pNext;
		}
	}
}
```

## 面试题19：正则表达式匹配