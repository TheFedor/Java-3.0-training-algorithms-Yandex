package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 5.Хорошая строка - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine());

        int[] arr = new int[N];
        int max = 0;

        for (int i = 0; i < N; ++i)
        {
            arr[i] = Integer.valueOf(br.readLine());
            if (arr[i] > max)
            {
                max = arr[i];
            }
        }

        br.close();

        long result = (long)0;

        if (N < 2)
        {
            System.out.println(result);
        }
        else
        {
            for (int i = 0; i < arr.length-1; ++i)
            {
                if (arr[i] <= arr[i+1])
                {
                    result = result + (long)arr[i];
                }
                else
                {
                    result = result + (long)arr[i+1];
                }
            }
            System.out.println(result);
        }
    }
}
