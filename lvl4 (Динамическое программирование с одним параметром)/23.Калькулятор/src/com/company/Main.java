package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());
        br.close();
        //long time = System.currentTimeMillis();

        int[] arrayOfSize = new int[N+1];
        int[] arrayOfWay = new int[N+1];

        arrayOfSize[1] = 1;

        int loopEnd = N/3;
        for (int i = 1; i <= loopEnd; ++i)
        {
            if (arrayOfSize[i*3] == 0 || arrayOfSize[i*3] > arrayOfSize[i]+1)
            {
                arrayOfSize[i*3] = arrayOfSize[i]+1;
                arrayOfWay[i*3] = i;
            }
            if (arrayOfSize[i*2] == 0 || arrayOfSize[i*2] > arrayOfSize[i]+1)
            {
                arrayOfSize[i*2] = arrayOfSize[i]+1;
                arrayOfWay[i*2] = i;
            }
            if (arrayOfSize[i+1] == 0 || arrayOfSize[i+1] > arrayOfSize[i]+1)
            {
                arrayOfSize[i+1] = arrayOfSize[i]+1;
                arrayOfWay[i+1] = i;
            }
        }

        loopEnd = N/2;
        for (int i = (N/3)+1; i <= loopEnd; ++i)
        {
            if (arrayOfSize[i*2] == 0 || arrayOfSize[i*2] > arrayOfSize[i]+1)
            {
                arrayOfSize[i*2] = arrayOfSize[i]+1;
                arrayOfWay[i*2] = i;
            }
            if (arrayOfSize[i+1] == 0 || arrayOfSize[i+1] > arrayOfSize[i]+1)
            {
                arrayOfSize[i+1] = arrayOfSize[i]+1;
                arrayOfWay[i+1] = i;
            }
        }

        loopEnd = N;
        for (int i = (N/2)+1; i < loopEnd; ++i)
        {
            if (arrayOfSize[i+1] == 0 || arrayOfSize[i+1] > arrayOfSize[i]+1)
            {
                arrayOfSize[i+1] = arrayOfSize[i]+1;
                arrayOfWay[i+1] = i;
            }
        }

        System.out.println((arrayOfSize[N]-1));
        List<Integer> way = new ArrayList<>();
        way.add(N);
        int wayIndex = arrayOfWay[N];
        while (wayIndex >= 1)
        {
            way.add(wayIndex);
            wayIndex = arrayOfWay[wayIndex];
        }
        StringBuilder stringWarianseOfWay = new StringBuilder();
        for (int i = way.size()-1; i >=0; --i)
        {
            stringWarianseOfWay.append(way.get(i)).append(" ");
        }
        System.out.println(stringWarianseOfWay);

        //System.out.println("time: " + (System.currentTimeMillis()-time) + "mSec");
        //long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        //System.out.println("memory: " + (usedBytes/1048576) + "Mb");

    }
}
