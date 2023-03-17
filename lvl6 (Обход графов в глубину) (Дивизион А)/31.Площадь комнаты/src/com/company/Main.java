package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 31.Площадь комнаты - lvl6 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        int[][] graph = new int[N+1][N+1];

        String line;
        for (int i = 1; i < N+1; ++i)
        {
            line = reader.readLine();
            for (int j = 1; j < N+1; ++j)
            {
                if (line.codePointAt(j-1) == 46)
                    graph[i][j] = 1;
            }
        }

        line = reader.readLine();
        reader.close();
        String[] lineArray = line.split(" ");

        int startI = Integer.valueOf(lineArray[0]);
        int startJ = Integer.valueOf(lineArray[1]);
        boolean[][] visited = new boolean[N+1][N+1];

        dfs(graph, visited, startI, startJ, N);

        int answer = 0;
        for (int i = 2; i < N; ++i)
            for (int j = 2; j < N; ++j)
                if (visited[i][j])
                    answer++;

        System.out.println(answer);

    }

    public static void dfs(int[][] graph, boolean[][] visited, int nowI, int nowJ, int N)
    {
        visited[nowI][nowJ] = true;

        if (nowI - 1 > 1) //вверх
        {
            if (!visited[nowI-1][nowJ] && graph[nowI-1][nowJ] > 0)
                dfs(graph, visited, nowI-1, nowJ, N);
        }
        if (nowJ + 1 < N) //вправо
        {
            if (!visited[nowI][nowJ+1] && graph[nowI][nowJ+1] > 0)
                dfs(graph, visited, nowI, nowJ+1, N);
        }
        if (nowI + 1 < N) //вниз
        {
            if (!visited[nowI+1][nowJ] && graph[nowI+1][nowJ] > 0)
                dfs(graph, visited, nowI+1, nowJ, N);
        }
        if (nowJ - 1 > 1) //влево
        {
            if (!visited[nowI][nowJ-1] && graph[nowI][nowJ-1] > 0)
                dfs(graph, visited, nowI, nowJ-1, N);
        }
    }

}
