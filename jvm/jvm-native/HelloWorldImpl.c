#include "jni.h"
#include "HelloWorld.h"
#include <stdio.h>

int recursiveAll(int depth);

JNIEXPORT void JNICALL Java_HelloWorld_hello(JNIEnv *env,jobject obj){
    printf("Hello World!\n");
    recursiveAll(1);
    return;
}


int recursiveAll(int depth) {
    if (depth < 1) {
        return depth;
    }
    printf("%d \n", depth);
    return recursiveAll(depth + 1);
}