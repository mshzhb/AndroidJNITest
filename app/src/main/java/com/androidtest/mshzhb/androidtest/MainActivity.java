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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_test);


        final TextView textViewPrimeTest = (TextView) findViewById(R.id.textViewPrime);
        Button buttonPrimeTest = (Button) findViewById(R.id.buttonPrime);

        buttonPrimeTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long primeTimeJava = primeTestJava();
                long primeTimeCPP = primeTestCPP();

                Double speedup = BigDecimal.valueOf((double)primeTimeJava/primeTimeCPP*100)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue();

                textViewPrimeTest.setText("Java time: " + primeTimeJava + " milliseconds\n" +
                        "C++ time: "+ primeTimeCPP + " milliseconds\n" +
                "Speedup: " + speedup +"%");
            }
        });
    }

    public long primeTestCPP(){
        long startTime = System.currentTimeMillis();
        primeTestCPPhelper();
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
    public native long primeTestCPPhelper();
}
