package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
	// 24.Покупка билетов - lvl4

        //long time = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());
        int[][] array = new int[N][3];
        String line;
        String[] lineArray;
        int[] min = new int[N+3];
        for (int i = 0; i < N; ++i)
        {
            line = br.readLine();
            lineArray = line.split(" ");
            array[i][0] = Integer.valueOf(lineArray[0]);
            array[i][1] = Integer.valueOf(lineArray[1]);
            array[i][2] = Integer.valueOf(lineArray[2]);

        }

        for (int i = 0; i < N; ++i)
        {
            if (i + 3 <= N)
            {
                if (min[i+3] == 0 || min[i+3] > min[i] + array[i][2])
                    min[i+3] = min[i] + array[i][2];
            }
            if (i + 2 <= N)
            {
                if (min[i+2] == 0 || min[i+2] > min[i] + array[i][1])
                    min[i+2] = min[i] + array[i][1];
            }
            if (i + 1 <= N)
            {
                if (min[i+1] == 0 || min[i+1] > min[i] + array[i][0])
                    min[i+1] = min[i] + array[i][0];
            }

        }

        br.close();

        System.out.println(min[N]);

        //System.out.println("time: " + (System.currentTimeMillis() - time));
    }

}
