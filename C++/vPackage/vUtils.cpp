//
// Created by vincent on 20-3-1.
//
#include "vUtils.h"

void vincent::gerCurwd()
{
    char *buffer;
    //也可以将buffer作为输出参数
    if((buffer = getcwd(NULL, 0)) == NULL)
    {
        perror("getcwd error");
    }
    else
    {
        printf("%s\n", buffer);
        free(buffer);
    }
}
