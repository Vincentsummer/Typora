#include <iostream>
#include <cstring>
#include "./vPackage/includes/vList.h"
#include "offerTest/DeleteDuplication_18_2.h"

using namespace std;

int main(void){
	LinkList pHead = nullptr;
	int n = 10;
	pHead = creatList_Order(n);

	DeleteDuplication(&pHead);
	PrintList(pHead);

}



