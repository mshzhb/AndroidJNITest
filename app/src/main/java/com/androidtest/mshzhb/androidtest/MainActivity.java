package com.androidtest.mshzhb.androidtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    public MathTestModel mathTestModel;
    public TestHelper testHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_test);

        mathTestModel = new MathTestModel();
        testHelper = new TestHelper();

        final TextView textViewPrimeTest = (TextView) findViewById(R.id.textViewPrime);
        Button buttonPrimeTest = (Button) findViewById(R.id.buttonPrime);

        final TextView textViewSumTest = (TextView) findViewById(R.id.textViewSum);
        Button buttonSumTest = (Button) findViewById(R.id.buttonSum);

        final TextView textViewPiTest = (TextView) findViewById(R.id.textViewPi);
        Button buttonPiTest = (Button) findViewById(R.id.buttonPi);


        //Prime Test
        buttonPrimeTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long primeTimeJava = mathTestModel.primeTestJava();
                long primeTimeCPP = mathTestModel.primeTestCPP();

                testHelper.cppTimeArray[TestHelper.PRIME_INDEX].add(primeTimeCPP);
                testHelper.javaTimeArray[TestHelper.PRIME_INDEX].add(primeTimeJava);

                double speedup = testHelper.speedup(primeTimeJava, primeTimeCPP);
                testHelper.speedUpArray[TestHelper.PRIME_INDEX].add(speedup);

                textViewPrimeTest.setText("Java time: " + primeTimeJava + " milliseconds\n" + "Avg Java time: " +  testHelper.average(testHelper.javaTimeArray[TestHelper.PRIME_INDEX]) + " milliseconds\n" +
                        "C++ time: "+ primeTimeCPP + " milliseconds\n" + "Avg C++ time: " +  testHelper.average(testHelper.cppTimeArray[TestHelper.PRIME_INDEX]) + " milliseconds\n" +
                        "Speedup: " + speedup +"%" + "\n" + "Avg Speedup: " + testHelper.averageDouble(testHelper.speedUpArray[TestHelper.PRIME_INDEX]) +"%");


            }
        });

        //Sum Test
        buttonSumTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long timeJava = mathTestModel.sumTestJava();
                long timeCpp = mathTestModel.sumTestCPP();

                testHelper.cppTimeArray[TestHelper.SUM_INDEX].add(timeCpp);
                testHelper.javaTimeArray[TestHelper.SUM_INDEX].add(timeJava);

                double speedup = testHelper.speedup(timeJava, timeCpp);
                testHelper.speedUpArray[TestHelper.SUM_INDEX].add(speedup);

                textViewSumTest.setText("Java time: " + timeJava + " milliseconds\n" + "Avg Java time: " +  testHelper.average(testHelper.javaTimeArray[TestHelper.SUM_INDEX]) + " milliseconds\n" +
                        "C++ time: "+ timeCpp + " milliseconds\n" + "Avg C++ time: " +  testHelper.average(testHelper.cppTimeArray[TestHelper.SUM_INDEX]) + " milliseconds\n" +
                        "Speedup: " + speedup +"%" + "\n" + "Avg Speedup: " + testHelper.averageDouble(testHelper.speedUpArray[TestHelper.SUM_INDEX]) +"%");




            }
        });

        //Pi Test
        buttonPiTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long timeJava = mathTestModel.piTestJavaHelper();
                long timeCpp = mathTestModel.piTestCppHelper();

                testHelper.cppTimeArray[TestHelper.PI_INDEX].add(timeCpp);
                testHelper.javaTimeArray[TestHelper.PI_INDEX].add(timeJava);

                double speedup = testHelper.speedup(timeJava, timeCpp);
                testHelper.speedUpArray[TestHelper.PI_INDEX].add(speedup);

                textViewPiTest.setText("Java time: " + timeJava + " milliseconds\n" + "Avg Java time: " +  testHelper.average(testHelper.javaTimeArray[TestHelper.PI_INDEX]) + " milliseconds\n" +
                        "C++ time: "+ timeCpp + " milliseconds\n" + "Avg C++ time: " +  testHelper.average(testHelper.cppTimeArray[TestHelper.PI_INDEX]) + " milliseconds\n" +
                        "Speedup: " + speedup +"%" + "\n" + "Avg Speedup: " + testHelper.averageDouble(testHelper.speedUpArray[TestHelper.PI_INDEX]) +"%");


            }
        });
    }




}
