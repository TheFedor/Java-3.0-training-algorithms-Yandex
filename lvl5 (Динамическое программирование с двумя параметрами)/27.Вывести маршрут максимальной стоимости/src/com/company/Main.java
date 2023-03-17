package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 27.Вывести маршрут максимальной стоимости - lvl5

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]);
        int M = Integer.valueOf(line[1]);

        int[][] numbers = new int[N][M];

        for (int i = 0; i < N; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = 0; j < M; ++j)
            {
                numbers[i][j] = Integer.valueOf(line[j]);
            }
        }
        reader.close();

        int[][][] answer = new int[N][M][2];

        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j)
                answer[i][j][0] = -1;
        answer[0][0][0] = numbers[0][0];
        answer[0][0][1] = -1;

        //считаем сумму и записываем ходы
        for (int i = 0; i < N; ++i)
        {
            for (int j = 0; j < M; ++j)
            {
                if (i + 1 < N)
                {
                    if (answer[i+1][j][0] < answer[i][j][0] + numbers[i+1][j])
                    {
                        answer[i+1][j][0] = answer[i][j][0] + numbers[i+1][j];
                        answer[i+1][j][1] = 1;
                    }
                }
                if (j + 1 < M)
                {
                    if (answer[i][j+1][0] < answer[i][j][0] + numbers[i][j+1])
                    {
                        answer[i][j+1][0] = answer[i][j][0] + numbers[i][j+1];
                        answer[i][j+1][1] = 2;
                    }
                }
            }
        }

        //выводим длину
        System.out.println(answer[N-1][M-1][0]);

        //восстанавливаем последовательность
        if (N + M > 2)
        {
            int[] theBiggestWay = new int[N+M-2];
            int i = N-1;
            int j = M-1;
            int counter = 0;
            do
            {
                theBiggestWay[counter] = answer[i][j][1];
                counter++;
                if (answer[i][j][1] == 2)
                    j--;
                else if (answer[i][j][1] == 1)
                    i--;
            }
            while (answer[i][j][1] != -1);
            StringBuilder way = new StringBuilder();
            for (int in = N+M-3; in >= 0; --in)
            {
                if (theBiggestWay[in] == 1)
                    way.append("D").append(" ");
                else
                    way.append("R").append(" ");
            }

            System.out.println(way);
        }
        else
            System.out.println();

    }
}
