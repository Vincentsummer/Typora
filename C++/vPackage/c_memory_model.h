/*
 * c_memory_model.h
 *
 *  Created on: Nov 18, 2019
 *      Author: vincent
 */

#ifndef C_MEMORY_MODEL_H_
#define C_MEMORY_MODEL_H_

#include <stdlib.h>
#include <string.h>
#include <stdio.h>

// 堆
char *getMem(int num){
	char *p1 = NULL;
	p1 = (char *)malloc(sizeof(char) * num);
	if (p1 == NULL){
		return NULL;
	}
	return p1;
}

// 栈
char *getMem2(){
	char buf[64];   //局部变量，栈区存放
	strcpy(buf, "123456789");
	return buf;
}

// 全局区
char *getStr1(){
	char *p1 = "abcdefg2";  // 常量
	char s = 'a';
	return &s;
}

char *getStr2(){
	char *p2 = "abcdefg2";
	return p2;
}

void c_mm(void){
	printf("%d", 10);
	char *tmp = NULL;
	tmp = getMem(10);
	if (tmp == NULL){
		return;
	}
	strcpy(tmp, "111222");
	tmp = getMem2();

	char *p1 = NULL;
	char *p2 = NULL;
	p1 = getStr1();
	p2 = getStr2();


	printf("tmp: %s \n", *tmp);
	printf("tmp: %p \n", tmp);
	printf("p1:%s,p2:%s \n", *p1, *p2);
	printf("p1:%p,p2:%p \n", p1, p2);
	return;
}
#endif /* C_MEMORY_MODEL_H_ */
