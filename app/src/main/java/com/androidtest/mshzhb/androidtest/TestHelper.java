package com.androidtest.mshzhb.androidtest;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mshzhb on 03/12/17.
 */

public class TestHelper {


    public static String[] TEST_STRING = {"Prime Test", "Sum Test", "Pi Test"};


    public static int PRIME_INDEX = 0;
    public static int SUM_INDEX = 1;
    public static int PI_INDEX = 2;

    public static int TOTAL_NUMUBER_OF_TEST = 3;

    public List[] javaTimeArray;
    public List[] cppTimeArray;
    public List[] speedUpArray;




    public TestHelper() {
        javaTimeArray = new List[TOTAL_NUMUBER_OF_TEST];
        cppTimeArray = new List[TOTAL_NUMUBER_OF_TEST];
        speedUpArray = new List[TOTAL_NUMUBER_OF_TEST];

        for (int i = 0; i < TOTAL_NUMUBER_OF_TEST; i++){
            javaTimeArray[i] = new ArrayList<Long>();
            cppTimeArray[i] = new ArrayList<Long>();
            speedUpArray[i] = new ArrayList<Double>();
        }

    }

    public double speedup(long timeJava, long timeCpp){
        try {
            double speedup = BigDecimal.valueOf((double) timeJava / timeCpp * 100)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            return speedup;
        }catch (Exception e){
            Log.e("Exception",e.toString());
        }
        return -1;
    }

    public String getTestReport(){
        String result = new String();

        for(int i = 0; i<TOTAL_NUMUBER_OF_TEST; i++){
            if(javaTimeArray[i].size() == 0 || cppTimeArray[i].size() == 0)
                continue;

            result +=  TEST_STRING[i] + ":\n" + "Number of Test Run: " + javaTimeArray[i].size() + "\n";
            result += "Avg Java Time: " + average(javaTimeArray[i]) + "\n";
            result += "Avg Cpp Time: " + average(cppTimeArray[i]) + "\n\n";
        }

        Log.d("Report", result);
        return result;
    }


    public double average(List<Long> list)
    {
        double average = 0.0;
        for (int i = 0; i < list.size(); i++)  {
            average += list.get(i);
        }

        return BigDecimal.valueOf(average/list.size())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double averageDouble(List<Double> list)
    {
        double average = 0.0;
        for (int i = 0; i < list.size(); i++)  {
            average += list.get(i);
        }

        return BigDecimal.valueOf(average/list.size())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }



}
