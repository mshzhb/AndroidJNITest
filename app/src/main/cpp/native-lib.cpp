#include <jni.h>
#include <string>
#include <time.h>
#include <stdio.h>
#include <math.h>
#include <sys/time.h>

long PRIME_MAX = 100000;
int my_is_prime(long a);

extern "C"
JNIEXPORT jstring

JNICALL
Java_com_androidtest_mshzhb_androidtest_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jlong

JNICALL
Java_com_androidtest_mshzhb_androidtest_MainActivity_primeTestCPPhelper(
        JNIEnv *env,
jobject /* this */
) {
    long num;
    for (num = 0; num < PRIME_MAX; num++)
        my_is_prime(num);

    return 0;

}

int my_is_prime(long a)
{
    long n;
    if(a == 2){
        return 1;
    }else if(a <= 1 || a % 2 == 0){
        return 0;
    }
    long max = sqrt(a);
    for( n= 3; n <= max; n+= 2){
        if(a % n == 0){ return 0; }
    }
    return 1;
}
