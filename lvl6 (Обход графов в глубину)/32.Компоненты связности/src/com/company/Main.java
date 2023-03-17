package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 32.Компоненты связности - lvl6

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

        //решение
        int numberOfComponents = 0;
        boolean[] visited = new boolean[N+1];
        List<ArrayList<Integer>> setOfComponents = new ArrayList<>();

        for (int i = 1; i < N+1; ++i)
        {
            if (!visited[i])
            {
                setOfComponents.add(new ArrayList<>());
                dfs(graph, visited, i, setOfComponents.get(numberOfComponents));
                numberOfComponents++;
            }
        }

        //вывод ответа
        System.out.println(setOfComponents.size());
        for (ArrayList<Integer> oneComponent : setOfComponents)
        {
            System.out.println(oneComponent.size());
            for (int i = 0; i < oneComponent.size(); ++i)
                System.out.print(oneComponent.get(i) + " ");
            System.out.println();
        }
    }

    public static void dfs(ArrayList<Integer>[] graph, boolean[] visited, int now, ArrayList<Integer> setOC)
    {
        visited[now] = true;
        setOC.add(now);
        for (int neig : graph[now])
            if (!visited[neig])
                dfs(graph, visited, neig, setOC);
    }
}
