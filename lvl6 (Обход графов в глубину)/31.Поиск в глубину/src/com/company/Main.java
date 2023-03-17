package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 31.Поиск в глубину - lvl6

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");
        int N = Integer.valueOf(line[0]); //число вершин
        int M = Integer.valueOf(line[1]); //число ребер

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; ++i)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; ++i)
        {
            line = reader.readLine().split(" ");
            int edgeOne = Integer.valueOf(line[0]);
            int edgeTwo = Integer.valueOf(line[1]);
            graph[edgeOne].add(edgeTwo);
            graph[edgeTwo].add(edgeOne);
        }
        reader.close();

        //поиск компоненты
        boolean[] visited = new boolean[N+1];
        dfs(graph, visited, 1);

        //находим ответ
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < N+1; ++i)
            if (visited[i])
                answer.add(i);

        //выводим ответ
        System.out.println(answer.size());
        for (int ver : answer)
            System.out.print(ver + " ");

    }

    public static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int now)
    {
        visited[now] = true;
        for (int neig : graph[now])
            if (!visited[neig])
                dfs(graph, visited, neig);
    }
}
