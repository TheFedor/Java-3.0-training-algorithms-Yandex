package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
	// 38.Блохи - lvl7

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]);
        int M = Integer.valueOf(line[1]);
        int S = Integer.valueOf(line[2]);
        int T = Integer.valueOf(line[3]);
        int Q = Integer.valueOf(line[4]);

        int[][] graph = new int[N+1][M+1];
        for (int i = 0; i < N+1; ++i)
            for (int j = 0; j < M+1; ++j)
                graph[i][j] = -1;
        graph[S][T] = 0;

        bfs(graph, S, T, N, M);

        //находим ответ
        boolean b = true; //проверка, все ли блохи дошли
        int answer = 0;

        for (int i = 0; i < Q; ++i)
        {
            line = reader.readLine().split(" ");
            int I = Integer.valueOf(line[0]);
            int J = Integer.valueOf(line[1]);
            answer += graph[I][J];
            if (graph[I][J] == -1)
            {
                b = false;
                break;
            }
        }
        reader.close();

        if (b)
        {
            System.out.println(answer);
        }
        else
            System.out.println(-1);

    }

    public static void bfs(int[][] graph, int S, int T, int N, int M)
    {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {S,T});

        while (!queue.isEmpty())
        {
            int Iwork = queue.get(0)[0];
            int Jwork = queue.get(0)[1];
            queue.remove(0);

            //1
            if (Iwork - 1 > 0 && Jwork + 2 < M + 1)
            {
                if (graph[Iwork-1][Jwork+2] == -1)
                {
                    graph[Iwork-1][Jwork+2] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork-1, Jwork+2});
                }
            }
            //2
            if (Iwork - 2 > 0 && Jwork + 1 < M + 1)
            {
                if (graph[Iwork-2][Jwork+1] == -1)
                {
                    graph[Iwork-2][Jwork+1] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork-2, Jwork+1});
                }
            }
            //3
            if (Iwork - 2 > 0 && Jwork - 1 > 0)
            {
                if (graph[Iwork-2][Jwork-1] == -1)
                {
                    graph[Iwork-2][Jwork-1] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork-2, Jwork-1});
                }
            }
            //4
            if (Iwork - 1 > 0 && Jwork - 2 > 0)
            {
                if (graph[Iwork-1][Jwork-2] == -1)
                {
                    graph[Iwork-1][Jwork-2] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork-1, Jwork-2});
                }
            }
            //5
            if (Iwork + 1 < N+1 && Jwork - 2 > 0)
            {
                if (graph[Iwork+1][Jwork-2] == -1)
                {
                    graph[Iwork+1][Jwork-2] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork+1, Jwork-2});
                }
            }
            //6
            if (Iwork + 2 < N+1 && Jwork - 1 > 0)
            {
                if (graph[Iwork+2][Jwork-1] == -1)
                {
                    graph[Iwork+2][Jwork-1] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork+2, Jwork-1});
                }
            }
            //7
            if (Iwork + 2 < N+1 && Jwork + 1 < M+1)
            {
                if (graph[Iwork+2][Jwork+1] == -1)
                {
                    graph[Iwork+2][Jwork+1] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork+2, Jwork+1});
                }
            }
            //8
            if (Iwork + 1 < N+1 && Jwork + 2 < M+1)
            {
                if (graph[Iwork+1][Jwork+2] == -1)
                {
                    graph[Iwork+1][Jwork+2] = graph[Iwork][Jwork] + 1;
                    queue.add(new int[] {Iwork+1, Jwork+2});
                }
            }
        }
    }
}
