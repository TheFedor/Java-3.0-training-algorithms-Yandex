package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
	// 39.Путь спелеолога - lvl7

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        int[][][] graph = new int[N][N][N];

        String[] line;
        int Ycord = 0;
        int Xcord = 0;
        int Hcord = 0;
        for (int i = 0; i < N; ++i)
        {
            reader.readLine();
            for (int j = 0; j < N; ++j)
            {
                line = reader.readLine().split("");
                for (int p = 0; p < N; ++p)
                    switch (line[p])
                    {
                        case ("#"):
                            graph[j][p][i] = -2;
                            break;
                        case ("."):
                            graph[j][p][i] = -1;
                            break;
                        default:
                            graph[j][p][i] = 0;
                            Ycord = j;
                            Xcord = p;
                            Hcord = i;
                            break;
                    }
            }
        }

        if (Hcord == 0)
            System.out.println(0);
        else
        {
            bfs(graph, Ycord, Xcord, Hcord, N);

            //проходим по верхнему слою и смотрим кратчайший путь
            int answer = 10000;
            for (int i = 0; i < N; ++i)
                for (int j = 0; j < N; ++j)
                    if (graph[i][j][0] > -1)
                    {
                        if (graph[i][j][0] < answer)
                            answer = graph[i][j][0];
                    }

            if (answer < 10000)
                System.out.println(answer);
            else
                System.out.println(-1);
        }

    }

    public static void bfs (int[][][] graph, int Ycord, int Xcord, int Hcord, int N)
    {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {Ycord, Xcord, Hcord});

        while (!queue.isEmpty())
        {
            int Ywork = queue.get(0)[0];
            int Xwork = queue.get(0)[1];
            int Hwork = queue.get(0)[2];
            queue.remove(0);
            //1
            if (Hwork + 1 < N)
            {
                if (graph[Ywork][Xwork][Hwork+1] == -1)
                {
                    graph[Ywork][Xwork][Hwork+1] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork, Xwork, Hwork+1});
                }
            }
            //2
            if (Hwork - 1 >= 0)
            {
                if (graph[Ywork][Xwork][Hwork-1] == -1)
                {
                    graph[Ywork][Xwork][Hwork-1] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork, Xwork, Hwork-1});
                }
            }
            //3
            if (Xwork + 1 < N)
            {
                if (graph[Ywork][Xwork+1][Hwork] == -1)
                {
                    graph[Ywork][Xwork+1][Hwork] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork, Xwork+1, Hwork});
                }
            }
            //4
            if (Xwork-1 >= 0)
            {
                if (graph[Ywork][Xwork-1][Hwork] == -1)
                {
                    graph[Ywork][Xwork-1][Hwork] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork, Xwork-1, Hwork});
                }
            }
            //5
            if (Ywork+1 < N)
            {
                if (graph[Ywork+1][Xwork][Hwork] == -1)
                {
                    graph[Ywork+1][Xwork][Hwork] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork+1, Xwork, Hwork});
                }
            }
            //6
            if (Ywork-1 >= 0)
            {
                if (graph[Ywork-1][Xwork][Hwork] == -1)
                {
                    graph[Ywork-1][Xwork][Hwork] = graph[Ywork][Xwork][Hwork] + 1;
                    queue.add(new int[] {Ywork-1, Xwork, Hwork});
                }
            }
        }
    }
}
