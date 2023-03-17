package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 26.Самый дешевый путь - lvl5

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]);
        int M = Integer.valueOf(line[1]);

        int[][] numbers = new int[N][M];
        int max = 0;
        for (int i = 0; i < N; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = 0; j < M; ++j)
            {
                numbers[i][j] = Integer.valueOf(line[j]);
                max += numbers[i][j];
            }
        }
        reader.close();

        int[][] answer = new int[N][M];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j)
                answer[i][j] = max+1;
        answer[0][0] = numbers[0][0];

        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < M; ++j)
            {
                if (i + 1 < N)
                {
                    if (answer[i+1][j] > answer[i][j] + numbers[i+1][j])
                        answer[i+1][j] = answer[i][j] + numbers[i+1][j];
                }
                if (j + 1 < M)
                {
                    if (answer[i][j+1] > answer[i][j] + numbers[i][j+1])
                        answer[i][j+1] = answer[i][j] + numbers[i][j+1];
                }

            }
        }

        System.out.println(answer[N-1][M-1]);
    }
}
