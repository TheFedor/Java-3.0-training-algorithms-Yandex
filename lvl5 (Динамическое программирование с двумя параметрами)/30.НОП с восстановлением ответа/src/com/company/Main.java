package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 30.НОП с восстановлением ответа - lvl5

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine().trim()); //длина первой последовательности
        String[] line = reader.readLine().trim().split(" ");
        int[] firstSeq = new int[N]; //первая последовательность
        for (int i = 0; i < N; ++i)
            firstSeq[i] = Integer.valueOf(line[i]);

        int M = Integer.valueOf(reader.readLine().trim()); //длина второй последовательности
        line = reader.readLine().trim().split(" ");
        reader.close();
        int[] secondSeq = new int[M]; //вторая последовательность
        for (int i = 0; i < M; ++i)
            secondSeq[i] = Integer.valueOf(line[i]);

        //решение
        int[][] dp = new int[N+1][M+1];
        for (int i = 1; i < N+1; ++i)
            for (int j = 1; j < M+1; ++j)
            {
                if (firstSeq[i-1] == secondSeq[j-1])
                    dp[i][j] = dp[i-1][j-1]+1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }


        if (dp[N][M] > 1)
        {
            int lineIndex = N;
            int columnIndex = M;
            List<Integer> theBiggestSubseq = new ArrayList<>(dp[N][M]);

            while (dp[lineIndex][columnIndex] != 1)
            {
                if (dp[lineIndex-1][columnIndex-1] + 1 == dp[lineIndex][columnIndex] && Math.max(dp[lineIndex][columnIndex-1], dp[lineIndex-1][columnIndex]) != dp[lineIndex][columnIndex])
                {
                    theBiggestSubseq.add(secondSeq[columnIndex-1]);
                    lineIndex--;
                    columnIndex--;
                }
                else
                {
                    if (dp[lineIndex-1][columnIndex] == dp[lineIndex][columnIndex])
                        lineIndex--;
                    else
                        columnIndex--;
                }
            }
            for (int i = columnIndex-1; i >= 0; --i)
                if (dp[lineIndex][i] == 0)
                {
                    theBiggestSubseq.add(secondSeq[i]);
                    break;
                }

            StringBuffer answer = new StringBuffer();
            for (int i = theBiggestSubseq.size()-1; i >= 0; --i)
                answer.append(theBiggestSubseq.get(i)).append(" ");
            System.out.println(answer);

        }
        else if (dp[N][M] == 1)
        {
            int answer = 0;
            for (int i = M; i >= 0; --i)
                if (dp[N][i] == 0)
                {
                    answer = secondSeq[i];
                    break;
                }
            System.out.println(answer);
        }
        else
            System.out.println();


    }
}
