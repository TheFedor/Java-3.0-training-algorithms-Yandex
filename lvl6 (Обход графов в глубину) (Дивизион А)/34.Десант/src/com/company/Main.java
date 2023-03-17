package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 34.Десант - lvl6 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");
        int N = Integer.valueOf(line[0]);
        int M = Integer.valueOf(line[1]);

        int[][] matrix = new int[N+2][M+2];
        int[][] colours = new int[N+2][M+2];
        for (int i = 0; i < M+2; ++i)
        {
            matrix[0][i] = 20000;
            matrix[N+1][i] = 20000;
            colours[0][i] = 2;
            colours[N+1][i] = 2;
        }
        for (int i = 1; i < N+1; ++i)
        {
            matrix[i][0] = 20000;
            matrix[i][M+1] = 20000;
            colours[i][0] = 2;
            colours[i][M+1] = 2;
        }

        for (int i = 1; i < N + 1; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; ++j)
                matrix[i][j+1] = Integer.valueOf(line[j]);
        }
        reader.close();

        int count = 0;
        while (count < N * M)
        {
            int minHeight = 20000;
            int XminHright = -1;
            int YminHeight = -1;
            for (int i = 1; i < N + 1; ++i)
            {
                for (int j = 1; j < M + 1; ++j)
                {
                    if (matrix[i][j] < minHeight && colours[i][j] == 0)
                    {
                        minHeight = matrix[i][j];
                        XminHright = j;
                        YminHeight = i;
                    }
                }
            }
            colours[YminHeight][XminHright] = 1;
            count++;

            count = func(matrix, colours, YminHeight, XminHright, count);
        }

        //ищем ответ
        int answer = 0;
        for (int i = 1; i < N + 1; ++i)
            for (int j = 1; j < M + 1; ++j)
                if (colours[i][j] == 1)
                    answer++;
        System.out.println(answer);

    }

    public static int func(int[][] matrix, int[][] colours, int indI, int indJ, int count)
    {
        if (colours[indI+1][indJ] == 0)
            if (matrix[indI+1][indJ] >= matrix[indI][indJ])
            {
                colours[indI+1][indJ] = 2;
                count++;
                count = func(matrix, colours, indI+1, indJ, count);
            }
        /*if (colours[indI+1][indJ+1] == 0)
            if (matrix[indI+1][indJ+1] >= matrix[indI][indJ])
            {
                colours[indI+1][indJ+1] = 2;
                count++;
                count = func(matrix, colours, indI+1, indJ+1, count);
            }*/
        if (colours[indI][indJ+1] == 0)
            if (matrix[indI][indJ+1] >= matrix[indI][indJ])
            {
                colours[indI][indJ+1] = 2;
                count++;
                count = func(matrix, colours, indI, indJ+1, count);
            }
        /*if (colours[indI-1][indJ+1] == 0)
            if (matrix[indI-1][indJ+1] >= matrix[indI][indJ])
            {
                colours[indI-1][indJ+1] = 2;
                count++;
                count = func(matrix, colours, indI-1, indJ+1, count);
            }*/
        if (colours[indI-1][indJ] == 0)
            if (matrix[indI-1][indJ] >= matrix[indI][indJ])
            {
                colours[indI-1][indJ] = 2;
                count++;
                count = func(matrix, colours, indI-1, indJ, count);
            }
        /*if (colours[indI-1][indJ-1] == 0)
            if (matrix[indI-1][indJ-1] >= matrix[indI][indJ])
            {
                colours[indI-1][indJ-1] = 2;
                count++;
                count = func(matrix, colours, indI-1, indJ-1, count);
            }*/
        if (colours[indI][indJ-1] == 0)
            if (matrix[indI][indJ-1] >= matrix[indI][indJ])
            {
                colours[indI][indJ-1] = 2;
                count++;
                count = func(matrix, colours, indI, indJ-1, count);
            }
        /*if (colours[indI+1][indJ-1] == 0)
            if (matrix[indI+1][indJ-1] >= matrix[indI][indJ])
            {
                colours[indI+1][indJ-1] = 2;
                count++;
                count = func(matrix, colours, indI+1, indJ-1, count);
            }*/

        return count;
    }
}
