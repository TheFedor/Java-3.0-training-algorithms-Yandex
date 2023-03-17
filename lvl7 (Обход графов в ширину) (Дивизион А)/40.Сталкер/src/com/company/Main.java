package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
	// 40.Сталкер - lvl7 (Дивизион A)

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String[] line = reader.readLine().trim().split(" ");

        int N = Integer.valueOf(line[0]); //число зданий
        int K = Integer.valueOf(line[1]); //число карт

        ArrayList<Integer>[][] graph = new ArrayList[N][K];

        for (int i = 0; i < K; ++i)
        {
            int loop = Integer.valueOf(reader.readLine().trim());

            for (int j = 0; j < loop; ++j)
            {
                line = reader.readLine().trim().split(" ");
                int first = Integer.valueOf(line[0]) - 1;
                int second = Integer.valueOf(line[1]) - 1;

                if (graph[first][i] == null)
                    graph[first][i] = new ArrayList<>();
                graph[first][i].add(second);

                if (graph[second][i] == null)
                    graph[second][i] = new ArrayList<>();
                graph[second][i].add(first);

            }

        }

        int[][] price = new int[N][K];
        bfs(graph, price, K, N);

        int min = 1000000000;
        for (int i = 0; i < K; ++i)
        {
            if (price[N-1][i] > 0 && price[N-1][i] < min)
                min = price[N-1][i];
        }

        if (min < 1000000000)
            System.out.println(min);
        else
            System.out.println(-1);

    }


    public static boolean test(ArrayList<Integer>[] toTest, int N)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] way = new int[N];
        way[0] = 1;

        while (!queue.isEmpty())
        {
            int work = queue.get(0);
            queue.remove(0);
            for (int neig : toTest[work])
            {
                if (way[neig] == 0)
                {
                    way[neig] = 1;
                    queue.add(neig);
                }
            }
        }

        if (way[N-1] == 0)
            return false;
        else
            return true;
    }

    public static void bfs(ArrayList<Integer>[][] graph, int[][] price, int K, int N)
    {
        int I;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < K; ++i)
        {
            queue.add(new int[] {0, i});
            price[0][i] = 1;
        }
        boolean[][] b = new boolean[N][K];

        while (!queue.isEmpty())
        {
            int workVertex = queue.peekFirst()[0];
            int workMap = queue.pollFirst()[1];
            if (!b[workVertex][workMap])
            {
                if (graph[workVertex][workMap] != null)
                    for (int neig : graph[workVertex][workMap])
                    {
                        if (price[neig][workMap] == 0 || price[neig][workMap] > price[workVertex][workMap])
                        {
                            price[neig][workMap] = price[workVertex][workMap];
                            queue.addFirst( new int[] {neig, workMap});
                        }
                    }
                I = (workMap + 1 ) % K;
                if (price[workVertex][I] == 0 || price[workVertex][I] > price[workVertex][workMap] + 1)
                {
                    for (int i = 0; i < K; ++i)
                    {
                        if (i == workMap)
                            continue;
                        price[workVertex][i] = price[workVertex][workMap] + 1;
                        queue.addLast(new int[] {workVertex, i});
                    }
                }
                b[workVertex][workMap] = true;
            }
        }
    }

}
