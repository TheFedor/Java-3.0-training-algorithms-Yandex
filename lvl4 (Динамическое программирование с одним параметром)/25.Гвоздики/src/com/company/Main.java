package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 25.Гвоздики - lvl4

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());

        String line = br.readLine();
        br.close();
        String[] lineArray = line.split(" ");
        int[] nails = new int[N];

        for (int i = 0; i < N; ++i)
            nails[i] = Integer.valueOf(lineArray[i]);
        Arrays.sort(nails);

        if (N == 2)
        {
            int first = nails[0];
            int second = nails[1];
            System.out.println(second-first);
        }
        else if (N == 3)
        {
            int first = nails[0];
            int second = nails[1];
            int third = nails[2];
            System.out.println((second-first)+(third-second));
        }
        else
        {
            int first = nails[1] - nails[0];
            int second = nails[2] - nails[1] + first;
            int third = 0;

            //System.out.println("start: " + "[first-" + first + "], [second-" + second + "], [third-" + third + "]" );
            for (int i = 3; i < N; ++i)
            {
                third = Math.min(first, second) + (nails[i] - nails[i-1]);
                //System.out.println(i + ": " + "[first-" + first + "], [second-" + second + "], [third-" + third + "]" );
                first = second;
                second = third;
            }
            System.out.println(third);

        }


    }

}
