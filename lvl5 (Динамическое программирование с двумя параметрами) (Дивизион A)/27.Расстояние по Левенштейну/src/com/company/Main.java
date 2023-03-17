package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 27. Расстояние по Левенштейну - lvl5 (Дивизион А)

        Scanner sc = new Scanner(System.in);

        String A = sc.nextLine();
        String B = sc.nextLine();
        sc.close();

        int[][] dp = new int[A.length()+1][B.length()+1];
        for (int i = 0; i < A.length()+1; ++i)
            for (int j = 0; j < B.length()+1; ++j)
            {
                if (i == 0)
                    dp[i][j] = j;
                else if (j == 0)
                    dp[i][j] = i;
                else
                {
                    if (A.charAt(i-1) == B.charAt(j-1))
                        dp[i][j] = dp[i-1][j-1];
                    else
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        System.out.println(dp[A.length()][B.length()]);
    }
}
