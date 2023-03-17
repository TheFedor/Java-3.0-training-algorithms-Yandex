package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 32.Откуда достижима первая вершина

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]); //число вершин
        int M = Integer.valueOf(line[1]); //число ребер

        ArrayList<Integer>[] reverseGraph = new ArrayList[N+1];

        for (int i = 0; i < N+1; ++i)
            reverseGraph[i] = new ArrayList<>();

        for (int i = 0; i < M; ++i)
        {
            line = reader.readLine().split(" ");
            int from = Integer.valueOf(line[0]);
            int to = Integer.valueOf(line[1]);

            reverseGraph[to].add(from);

        }

        //решение
        List<Integer> answer = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int sch = 0;
        answer.add(1);
        visited.add(1);
        while (sch < answer.size())
        {
            int now = answer.get(sch);
            for (int i = 0; i < reverseGraph[now].size(); ++i)
            {
                if (!visited.contains(reverseGraph[now].get(i)))
                {
                    answer.add(reverseGraph[now].get(i));
                    visited.add(reverseGraph[now].get(i));
                }

            }
            sch++;
        }

        Collections.sort(answer);
        for (int i = 0; i < answer.size(); ++i)
            System.out.print(answer.get(i) + " ");
    }
}
