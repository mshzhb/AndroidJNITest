#include <jni.h>
#include <string>
#include <math.h>


int my_is_prime(long a);



extern "C"
JNIEXPORT jstring

JNICALL
Java_com_androidtest_mshzhb_androidtest_MathTestModel_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

double calc1(int num1);
double calc2(int num1);

extern "C"
JNIEXPORT jdouble

JNICALL
Java_com_androidtest_mshzhb_androidtest_MathTestModel_piTestCPP(
        JNIEnv *env,
        jobject /* this */,
        jint limit
) {
    int j = 0; /* The counter for the loop */
    double ans1 = 0.0;
    double ans2 = 0.0;
    double ans3 = 0; /* The final answer*/
    int flag = 1; /* 1 means addition and 0 means subtraction */
    for(j = 1; j <= limit ; j+= 2){
        if(flag == 1){
            ans1 += calc1(j);
            ans2 += calc2(j);
            flag = 0;
        } else{
            ans1 -= calc1(j);
            ans2 -= calc2(j);
            flag = 1;
        }
    }
    ans3 = ans1 - ans2;
    return ans3;
}

double calc1(int num1)
{
    return (16/(num1 * pow(5.0,num1 * 1.0)));
}
double calc2(int num1)
{
    return (4/(num1 * pow(239.0,num1 * 1.0)));
}


extern "C"
JNIEXPORT jlong

JNICALL
Java_com_androidtest_mshzhb_androidtest_MathTestModel_primeTestCPPhelper(
        JNIEnv *env,
        jobject /* this */,
        jlong PRIME_MAX
) {
    long num;
    for (num = 0; num < PRIME_MAX; num++)
        my_is_prime(num);

    return 0;

}

extern "C"
JNIEXPORT jlong

JNICALL
Java_com_androidtest_mshzhb_androidtest_MathTestModel_sumTestCPP(
        JNIEnv *env,
        jobject /* this */,
        jlong SUM_MAX
) {
    long num;
    long sum = 0;
    for (num = 0; num < SUM_MAX; num++)
        sum += num;

    return sum;

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
