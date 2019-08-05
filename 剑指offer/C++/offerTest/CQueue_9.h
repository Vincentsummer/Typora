#ifndef CQUEUE_9_H_
#define CQUEUE_9_H_

/**
 * ����������
 * 1. ���յĶ�������ӡ�ɾ��Ԫ�ء�
 * 2. ���ǿյĶ�������ӡ�ɾ��Ԫ�ء�
 * 3. ����ɾ��Ԫ��ֱ������Ϊ�ա�
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
