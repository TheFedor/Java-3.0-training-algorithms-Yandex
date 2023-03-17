package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
	// 36.Длина кратчайшего пути - lvl7

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        String[] line;
        ArrayList<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; ++i)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < N-1; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = i+1; j < line.length; ++j)
            {
                if (line[j].equals("1"))
                {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        reader.readLine();
        line = reader.readLine().split(" ");
        reader.close();

        int[] queue = new int[N];
        int queueStart = 0;
        int queueEnd = 0;
        queue[queueEnd] = Integer.valueOf(line[0]) - 1;
        queueEnd++;
        int[] wayLength = new int[N];
        Arrays.fill(wayLength, -1);
        wayLength[queue[queueStart]] = 0;

        while (queueStart != queueEnd)
        {
            int workElement = queue[queueStart];
            queueStart++;
            for (int i = 0; i < graph[workElement].size(); ++i)
            {
                if (wayLength[graph[workElement].get(i)] == -1)
                {
                    wayLength[graph[workElement].get(i)] = wayLength[workElement] + 1;
                    queue[queueEnd] = graph[workElement].get(i);
                    queueEnd++;
                }
            }
        }
        System.out.println(wayLength[Integer.valueOf(line[1])-1]);

    }
}
