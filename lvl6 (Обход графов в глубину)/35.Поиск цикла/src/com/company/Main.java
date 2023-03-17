package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 35.Поиск цикла - lvl6

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; ++i)
            graph[i] = new ArrayList<>();
        String[] line;

        for (int i = 1; i < N+1; ++i)
        {
            line = reader.readLine().split(" ");

            for (int j = 0; j < line.length; ++j)
                if (line[j].equals("1"))
                {
                    graph[i].add(j+1);
                }
        }

        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[N+1];
        boolean b = true; //true - нет циклов

        for (int i = 1; i < N+1; ++i)
        {
            if (!visited[i])
                b = dfs(graph, visited, i, -1, answer, b);
            if (!b)
                break;
        }

        if (b)
            System.out.println("NO");
        else
        {
            System.out.println("YES");
            //удаляем из ответной последовательности все лишние вершины
            while (!answer.get(answer.size()-1).equals(answer.get(0)))
            {
                answer.remove(answer.size()-1);
            }
            answer.remove(0);
            System.out.println(answer.size());
            for (int i = 0; i < answer.size(); ++i)
                System.out.print(answer.get(i) + " ");

        }

    }

    public static boolean dfs(ArrayList<Integer>[] graph, boolean[] visited, int now, int parent, List<Integer> answer, boolean b)
    {
        visited[now] = true;
        for (int neig : graph[now])
        {
            if (!visited[neig])
                b = dfs(graph, visited, neig, now, answer, b);
            else if (neig != parent)
            {
                answer.add(neig);
                b = false;
            }
            if (!b)
                break;
        }
        if (!b)
            answer.add(now);
        return b;
    }

}
