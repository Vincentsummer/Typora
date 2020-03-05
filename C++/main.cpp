/*
 * main.cpp
 *
 *  Created on: Nov 7, 2019
 *      Author: vincent
 */

#include <stdio.h>
#include "vPackage/vList/vList.h"

int main(void){
//	char buf[] = "bbbb";
	const char a = '1';
	char *p = (char *) &a;
	*p = '2';
//	buf[1] = 'c';
//	*(p+1) = '2';
	printf("%c", *p);
	return 0;
}




