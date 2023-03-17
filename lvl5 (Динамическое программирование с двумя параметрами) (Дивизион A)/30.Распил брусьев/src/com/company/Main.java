package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 30.Распил брусьев - lvl5 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int L = Integer.valueOf(line[0]); //Длина бруска
        int N = Integer.valueOf(line[1]); //Число распилов

        line = reader.readLine().split(" ");
        reader.close();

        long answer = 0;
        int[] numbers = new int[N+2];
        numbers[0] = 0;
        for (int i = 0; i < N; ++i)
            numbers[i+1] = Integer.valueOf(line[i]);
        numbers[N+1] = L;

        long[][] dp = new long[N+2][N+2];

        int sch = 1;
        while (sch < N+2)
        {
            for (int i = 0; i < N+2; ++i)
            {
                if (i + sch == N+2)
                    break;
                dp[i][i+sch] = numbers[i+sch]-numbers[i];
                double min = Double.POSITIVE_INFINITY;
                for (int j = i+1; j < i+sch; ++j)
                {
                    min = Math.min(min, dp[i][j] + dp[j][i+sch]);
                }
                if (min < Double.POSITIVE_INFINITY)
                    dp[i][i+sch] += min;
            }
            sch++;
        }

        System.out.println(dp[0][N+1] - L);

    }
}
