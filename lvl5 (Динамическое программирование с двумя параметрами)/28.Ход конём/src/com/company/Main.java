package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 28.Ход конём - lvl5

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.close();

        int[][] answer = new int[N][M];
        answer[0][0] = 1;

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j)
            {
                if (i + 2 < N)
                    if (j + 1 < M)
                        answer[i+2][j+1] += answer[i][j];
                if (i + 1 < N)
                    if (j + 2 < M)
                        answer[i+1][j+2] += answer[i][j];

            }
        System.out.println(answer[N-1][M-1]);
    }
}
