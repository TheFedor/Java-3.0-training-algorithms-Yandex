package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 39.Роботы - lvl7 (Дивизион A)

        //long time = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String[] line = reader.readLine().trim().split(" ");

        int N = Integer.valueOf(line[0]);
        int K = Integer.valueOf(line[1]);

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; ++i)
            graph[i] = new ArrayList<>();
        boolean[][] edges = new boolean[N+1][N+1];

        for (int i = 0; i < K; ++i)
        {
            line = reader.readLine().trim().split(" ");
            int first = Integer.valueOf(line[0]);
            int second = Integer.valueOf(line[1]);
            if (first == second)
            {
                graph[first].add(second);
                edges[first][second] = true;
            }
            else
            {
                graph[first].add(second);
                graph[second].add(first);
                edges[first][second] = true;
                edges[second][first] = true;
            }
        }

        int M = Integer.valueOf(reader.readLine().trim());
        line = reader.readLine().trim().split(" ");
        reader.close();

        Set<Integer> robs = new HashSet<>(M);
        for (int i = 0; i < M; ++i)
            robs.add(Integer.valueOf(line[i]));
        M = robs.size();

        int count = 0;
        int[] robots = new int[M];
        for (int rob : robs)
        {
            robots[count] = rob;
            count++;
        }

        if (testingBfs(graph, robots, N, robots[0]))
        {
            boolean[] haveOdd = new boolean[N + 1];
            boolean[] haveEven = new boolean[N + 1];
            int[] maxOdd = new int[N + 1];
            int[] maxEven = new int[N + 1];

            int[][] allEdges = new int[M][N + 1];

            for (int i = 0; i < M; ++i) {
                int[] localTimeOdd = new int[N + 1];
                int[] localTimeEven = new int[N + 1];
                Arrays.fill(localTimeOdd, -1);
                Arrays.fill(localTimeEven, -1);

                bfs(graph, localTimeOdd, localTimeEven, robots[i], i + 1);

                //теперь записываем результат по роботу в общую табличку (только для вершин)
                for (int j = 1; j < N + 1; ++j) {
                    if (localTimeEven[j] != -1 && !haveEven[j]) {
                        if (localTimeEven[j] > maxEven[j])
                            maxEven[j] = localTimeEven[j];
                    } else
                        haveEven[j] = true;
                    if (localTimeOdd[j] != -1 && !haveOdd[j]) {
                        if (localTimeOdd[j] > maxOdd[j])
                            maxOdd[j] = localTimeOdd[j];
                    } else
                        haveOdd[j] = true;

                    if (localTimeEven[j] >= 0 && localTimeOdd[j] >= 0) {
                        allEdges[i][j] = (int) Math.min(localTimeEven[j], localTimeOdd[j]);
                    } else if (localTimeEven[j] >= 0) {
                        allEdges[i][j] = localTimeEven[j];
                    } else if (localTimeOdd[j] >= 0) {
                        allEdges[i][j] = localTimeOdd[j];
                    } else {
                        allEdges[i][j] = -1;
                    }

                }
            }

            //теперь обрабатываем результаты проходов по вершинам
            boolean b = true;
            int answer1 = 1000000000;
            for (int i = 1; i < N + 1; ++i) {
                if (!haveEven[i]) {
                    if (answer1 > maxEven[i])
                        answer1 = maxEven[i];
                }
                if (!haveOdd[i]) {
                    if (answer1 > maxOdd[i])
                        answer1 = maxOdd[i];
                }

            }

            //теперь обрабатываем случаи, когда роботы встретились в туннеле
            double answer2 = 1000000000;
            //int cc = 0;

            for (int i = 1; i < N; ++i)
            {
                for (int j = i+1; j < N+1; ++j)
                {
                    if (edges[i][j])
                    {
                        //cc++;
                        double maxMin = -1;
                        for (int p = 0; p < M; ++p)
                        {
                            double min = Math.min(allEdges[p][i], allEdges[p][j]) + 0.5;
                            if (min > maxMin)
                                maxMin = min;
                        }
                        if (answer2 > maxMin && maxMin >= 0.5)
                            answer2 = maxMin;
                    }
                }
            }

            //теперь выводим ответ
            if (answer2 < answer1)
                System.out.println(answer2);
            else
                System.out.println(answer1);
            //System.out.println("time: " + (System.currentTimeMillis()-time));
            //System.out.println(cc);
            //System.out.println(edges.size());

        }
        else
            System.out.println(-1);


    }

    public static boolean testingBfs(ArrayList<Integer>[] graph, int[] robots, int N, int start)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        int[] localTime = new int[N+1];
        localTime[start] = 1;

        while (!queue.isEmpty())
        {
            int work = queue.get(0);
            queue.remove(0);

            for (int neig : graph[work])
            {
                if (localTime[neig] == 0)
                {
                    localTime[neig] = 1;
                    queue.add(neig);
                }
            }
        }

        boolean b = true;
        for (int i = 0; i < robots.length; ++i)
            if (localTime[robots[i]] < 1)
            {
                b = false;
                break;
            }
        return b;
    }

    public static void bfs(ArrayList<Integer>[] graph, int[] localTimeOdd, int[] localTimeEven, int start, int I)
    {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(start);
        localTimeEven[start] = 0;

        while (!queue.isEmpty())
        {
            int work = queue.get(0);
            queue.remove(0);

            for (int neig : graph[work])
            {
                if (localTimeEven[work] > -1)
                {
                    if (localTimeOdd[neig] == -1)
                    {
                        localTimeOdd[neig] = localTimeEven[work] + 1;
                        queue.add(neig);
                    }
                }
                if (localTimeOdd[work] > -1)
                {
                    if (localTimeEven[neig] == -1)
                    {
                        localTimeEven[neig] = localTimeOdd[work] + 1;
                        queue.add(neig);
                    }
                }
            }
        }
    }

}
