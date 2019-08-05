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

​	写一个函数，输入 $n$ ，求斐波那契数列的第 $n$ 项。斐波那契数列的定义如下：
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

​	一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。求该青蛙跳上一个 $n$ 级台阶总共有多少跳法。

**解法：同斐波那契数列。**



## 面试题11：旋转数组的最小数字

