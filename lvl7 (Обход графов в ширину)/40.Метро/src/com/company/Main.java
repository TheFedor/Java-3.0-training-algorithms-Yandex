package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 40.Метро - дмд7

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine()); //число станций
        int M = Integer.valueOf(reader.readLine()); //число веток

        Set<Integer> set = new HashSet<>();
        int[] flags = new int[N+1];
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; ++i)
            graph[i] = new ArrayList<>();

        String[] line;
        for (int i = 0; i < M; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = 1; j < line.length-1; ++j)
            {
                if (!set.contains(Integer.valueOf(line[j])))
                    set.add(Integer.valueOf(line[j]));
                else
                    flags[Integer.valueOf(line[j])] = 1;
                for (int p = j+1; p < line.length; ++p)
                {
                    graph[Integer.valueOf(line[j])].add(Integer.valueOf(line[p]));
                    graph[Integer.valueOf(line[p])].add(Integer.valueOf(line[j]));
                }
            }
            if (!set.contains(Integer.valueOf(line[line.length-1])))
                set.add(Integer.valueOf(line[line.length-1]));
            else
                flags[Integer.valueOf(line[line.length-1])] = 1;
        }

        line = reader.readLine().split(" ");
        reader.close();

        int start = Integer.valueOf(line[0]);
        int end = Integer.valueOf(line[1]);
        flags[start] = 0;
        flags[end] = 0;

        int[] way = new int[N+1];
        Arrays.fill(way, -1);
        way[start] = 0;

        bfs(graph, start, flags, way);

        System.out.println(way[end]);

    }

    public static void bfs(ArrayList<Integer>[] graph, int start, int[] flags, int[] way)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty())
        {
            int workElement = queue.get(0);
            if (flags[workElement] == 1)
                way[workElement] += 1;
            queue.remove(0);
            for (int neig : graph[workElement])
            {
                if (way[neig] == -1)
                {
                        way[neig] = way[workElement];
                        queue.add(neig);
                }
            }
        }
    }

}
