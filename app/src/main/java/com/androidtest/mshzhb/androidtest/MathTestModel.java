package com.androidtest.mshzhb.androidtest;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;

/**
 * Created by mshzhb on 03/12/17.
 */

public class MathTestModel {

    public long PRIME_MAX = 100000;
    public long SUM_MAX = 100000000;
    public int PI_LIMIT = 100000;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    public long piTestCppHelper(){
        long startTime = System.currentTimeMillis();
        double pi = piTestCPP(PI_LIMIT);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Log.d("C++ Pi", String.valueOf(pi));
        return elapsedTime;
    }

    long piTestJavaHelper(){
        long startTime = System.currentTimeMillis();
        double pi = piTestJava();
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Log.d("Java Pi", String.valueOf(pi));
        return elapsedTime;
    }

    double calc1(int num1) {
        return (16/(num1 * pow(5.0,num1 * 1.0)));
    }

    double calc2(int num1) {
        return (4/(num1 * pow(239.0,num1 * 1.0)));
    }

    double piTestJava(){
        int j = 0; /* The counter for the loop */
        double ans1 = 0.0;
        double ans2 = 0.0;
        double ans3 = 0.0; /* The final answer*/
        int flag = 1; /* 1 means addition and 0 means subtraction */
        for(j = 1; j <= PI_LIMIT ; j+= 2){
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




    public long sumTestJava(){
        long number;
        long startTime = System.currentTimeMillis();

        long sum = 0;
        for (number=0; number<SUM_MAX; number++)
            sum += number;

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        Log.d("Sum Test", String.valueOf(sum));
        return elapsedTime;
    }

    public long sumTestCPP(){
        long sum;
        long startTime = System.currentTimeMillis();
        sum = sumTestCPP(SUM_MAX);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        return elapsedTime;
    }


    public long primeTestCPP(){
        long startTime = System.currentTimeMillis();
        primeTestCPPhelper(PRIME_MAX);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        return elapsedTime;
    }

    public long primeTestJava(){
        long number;
        long startTime = System.currentTimeMillis();

        for (number=0; number<PRIME_MAX; number++)
            isprime(number);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        return elapsedTime;
    }

    public boolean isprime(long a)
    {
        if(a == 2){
            return true;
        }else if(a <= 1 || a % 2 == 0){
            return false;
        }
        long max = (long)Math.sqrt(a);
        for(long n= 3; n <= max; n+= 2){
            if(a % n == 0){ return false; }
        }
        return true;
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public native long primeTestCPPhelper(long PRIME_MAX);
    public native long sumTestCPP(long SUM_MAX);
    public native double piTestCPP(int PI_LIMIT);
}
