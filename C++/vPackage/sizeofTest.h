//
// Created by vincent on 20-2-2.
//

#ifndef CPP_SIZEOFTEST_H
#define CPP_SIZEOFTEST_H

#include <stdio.h>

namespace vincent{

    int sizeofTest(void){
        int a = 10;
        int b[5];
        //printf("b:%p, b+1:%p, &b:%p, &b+1:%p\n",b, b+1, &b, &b+1);
        printf("sizeof(a):%d \n",sizeof(a));
        printf("sizeof(int *):%d \n",sizeof(int *));
        printf("sizeof(b):%d \n", sizeof(b));
        printf("sizeof(b[0]):%d \n", sizeof(b[0]));
        printf("sizeof(*b):%d \n",sizeof(*b));
        return 0;
    }
}   // namespace vincent

#endif //CPP_SIZEOFTEST_H
