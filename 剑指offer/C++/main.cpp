#include <iostream>
#include <cstring>

#include "myPackage/includes/mySort/myBinarySearch.h"

using namespace std;

int main(void){
	int a[] = {1,2,3,4,5,7,8,9};
	int length = 9;
	int k = 7;
	int result = binarySearch(k, a, length);
	cout << result << endl;

}



