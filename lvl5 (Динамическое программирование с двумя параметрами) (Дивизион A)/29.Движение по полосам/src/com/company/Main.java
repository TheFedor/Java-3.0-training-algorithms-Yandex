package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 29.Движение по полосам - lvl5 (Дивизион A)

        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt(); //число дорог
        int n = sc.nextInt(); //число полос
        sc.close();

        //long time = System.currentTimeMillis();

        //решение

        long[][] dp = new long[n][m];
        dp[0][0] = 1;

        for (int i = 0; i < n; ++i)
        {
            for (int j = 0; j < m; ++j)
            {
                if (i != n-1)
                    dp[i+1][j] += dp[i][j];
                if (i != n-1 && j != m-1)
                    dp[i+1][j+1] += dp[i][j];
                if (j != m-1)
                    dp[i][j+1] += dp[i][j];
            }
        }

        System.out.println(dp[n-1][m-1]);
        //System.out.println("time: " + (System.currentTimeMillis()-time));

    }
}
