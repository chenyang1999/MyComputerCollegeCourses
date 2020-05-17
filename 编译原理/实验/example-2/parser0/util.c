/*
 * util.c - commonly used utility functions.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "util.h"

/* 检查并分配长度为len的内存空间，然后返回这块内存空间的指针 */
void *checked_malloc(int len){
	void *p = malloc(len);
 	if (!p) {
    fprintf(stderr,"\nRan out of memory!\n");
    exit(1);
 	}
 	return p;
}

/* 将串s重新拷贝一份并返回。返回的串具有自己的内存空间。*/
string String(char *s){
	string p = checked_malloc(strlen(s)+1);
 	strcpy(p,s);
 	return p;
}
