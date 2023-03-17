package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
	// 34.Топологическая сортировка - lvl6

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]); //число вершин
        int M = Integer.valueOf(line[1]); //число ребер

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; ++i)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; ++i)
        {
            line = reader.readLine().split(" ");
            int from = Integer.valueOf(line[0]);
            int to = Integer.valueOf(line[1]);

            graph[from].add(to);
        }

        byte[] colour = new byte[N+1];
        boolean b = true; //true - без циклов
        List<Integer> answer = new ArrayList<>(N+1);

        for (int i = 1; i < N+1; ++i)
        {
            if (colour[i] == 0)
                b = dfs(graph, colour, i, answer, b);
        }

        if (b)
        {
            Collections.reverse(answer);
            for (int ans : answer)
                System.out.print(ans + " ");
        }
        else
            System.out.println(-1);

    }

    public static boolean dfs(ArrayList<Integer>[] graph, byte[] colour, int now, List<Integer> answer, boolean b)
    {
        if (b)
        {
            colour[now] = 1;
            for (int neig : graph[now])
            {
                if (colour[neig] == 0)
                {
                    b = dfs(graph, colour, neig, answer, b);
                }
                else if (colour[neig] == 1)
                {
                    b = false;
                    break;
                }
            }
        }
        colour[now] = 2;
        answer.add(now);
        return b;
    }
}
