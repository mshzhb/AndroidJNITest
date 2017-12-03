package com.androidtest.mshzhb.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public long PRIME_MAX = 100000;
    public long SUM_MAX = 10000000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_test);


        final TextView textViewPrimeTest = (TextView) findViewById(R.id.textViewPrime);
        Button buttonPrimeTest = (Button) findViewById(R.id.buttonPrime);

        final TextView textViewSumTest = (TextView) findViewById(R.id.textViewSum);
        Button buttonSumTest = (Button) findViewById(R.id.buttonSum);

        //Prime Test
        buttonPrimeTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long primeTimeJava = primeTestJava();
                long primeTimeCPP = primeTestCPP();

                Double speedup = speedup(primeTimeJava, primeTimeCPP);

                textViewPrimeTest.setText("Java time: " + primeTimeJava + " milliseconds\n" +
                        "C++ time: "+ primeTimeCPP + " milliseconds\n" +
                "Speedup: " + speedup +"%");
            }
        });

        //Sum Test
        buttonSumTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long timeJava = sumTestJava();
                long timeCpp = sumTestCPP();

                Double speedup = speedup(timeJava, timeCpp);

                textViewSumTest.setText("Java time: " + timeJava + " milliseconds\n" +
                        "C++ time: "+ timeCpp + " milliseconds\n" +
                        "Speedup: " + speedup +"%");
            }
        });
    }

    public double speedup(long timeJava, long timeCpp){
        return BigDecimal.valueOf((double)timeJava/timeCpp*100)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public long sumTestJava(){
        long number;
        long startTime = System.currentTimeMillis();

        int sum = 0;
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
}
