package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 9.Сумма в прямоугольнике - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String[] strArray = str.split(" ");

        int N = Integer.valueOf(strArray[0]); //число строк
        int M = Integer.valueOf(strArray[1]); //число столбцов
        int K = Integer.valueOf(strArray[2]); //число запросов

        //создание и заполнение матрицы
        int[][] matrix = new int[M][N];

        for (int i = 0; i < N; ++i)
        {
            str = br.readLine();
            strArray = str.split(" ");

            for (int j = 0; j < M; ++j)
            {
                matrix[j][i] = Integer.valueOf(strArray[j]);
            }
        }

        //создание и заполнение матрицы-суммы
        int[][] sum = new int[M][N];

        sum[0][0] = matrix[0][0];

        for (int j = 1; j < M; ++j)
        {
            sum[j][0] = sum[j-1][0] + matrix[j][0];
        }

        for (int i = 1; i < N; ++i)
        {
            sum[0][i] = sum[0][i-1] + matrix[0][i];
        }

        for (int i = 1; i < N; ++i)
        {
            for (int j = 1; j < M; ++j)
            {
                sum[j][i] = sum[j][i-1] + sum[j-1][i] - sum[j-1][i-1] + matrix[j][i];
            } //j - столбец i - строка
        }

        //ответы на запросы

        int[] resultArray = new int[K];

        for (int i = 0; i < K; ++i)
        {
            str = br.readLine();
            strArray = str.split(" ");

            int x1 = Integer.valueOf(strArray[0])-1;
            int y1 = Integer.valueOf(strArray[1])-1;
            int x2 = Integer.valueOf(strArray[2])-1;
            int y2 = Integer.valueOf(strArray[3])-1;

            if (x1 > 0 && y1 > 0)
            {
                int result = sum[y2][x2] - sum[y2][x1-1] - sum[y1-1][x2] + sum[y1-1][x1-1];
                resultArray[i] = result;
            }
            else if (x1 == 0 && y1 == 0)
            {
                resultArray[i] = sum[y2][x2];
            }
            else if (x1 == 0)
            {
                resultArray[i] = sum[y2][x2] - sum[y1-1][x2];
            }
            else if (y1 == 0)
            {
                resultArray[i] = sum[y2][x2] - sum[y2][x1-1];
            }
        }

        br.close();

        //вывод ответов
        for (int i = 0; i < K; ++i)
        {
            System.out.println(resultArray[i]);
        }
    }
}
