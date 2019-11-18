#ifndef CQUEUE_9_H_
#define CQUEUE_9_H_

/**
 * 测试用例：
 * 1. 往空的队列里添加、删除元素。
 * 2. 往非空的队列里添加、删除元素。
 * 3. 连续删除元素直至队列为空。
 * **/

#include <stack>

template <class T>
class CQueue{
public:
//	CQueue(void);
//	~CQueue(void);

	void appendTail(const T& node);
	T deleteHead();

private:
	std::stack<T> stack1;
	std::stack<T> stack2;
};

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

#endif /* CQUEUE_9_H_ */
