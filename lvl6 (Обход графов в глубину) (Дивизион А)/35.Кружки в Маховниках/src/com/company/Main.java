package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 35.Кружки в Маховниках - lvl6 (Дивизион А)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; ++i)
            graph[i] = new ArrayList<>();

        String[] line;
        for (int i = 1; i < N+1; ++i)
        {
            line = reader.readLine().split(" ");

            for (int j = 1; j < line.length; ++j)
            {
                graph[i].add(Integer.valueOf(line[j]));
            }
        }

        int[] manys = new int[N+1];
        for (int i = 1; i < N+1; ++i)
        {
            for (int j = 0; j < graph[i].size(); ++j)
                manys[graph[i].get(j)] += 1;
        }

        //проводим топологическую сортировку


        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i < N+1; ++i)
            if (manys[i] == 0)
            {
                queue.add(i);
                manys[i] = 100000000;
            }

        while (!queue.isEmpty())
        {
            int workElement = queue.poll();
            answer.add(workElement);
            for (int i = 0; i < graph[workElement].size(); ++i)
            {
                manys[graph[workElement].get(i)] -= 1;
                if (manys[graph[workElement].get(i)] == 0)
                {
                    queue.add(graph[workElement].get(i));
                    manys[graph[workElement].get(i)] = 100000000;
                }
            }
        }

        Collections.reverse(answer);
        for (int i = 0; i < answer.size(); ++i)
            System.out.print(answer.get(i) + " ");

    }
}
