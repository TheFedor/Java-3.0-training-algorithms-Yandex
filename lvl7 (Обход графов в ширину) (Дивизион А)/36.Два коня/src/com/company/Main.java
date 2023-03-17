package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
	// 36.Два коня - lvl7 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");
        reader.close();

        int firstX = line[0].codePointAt(0) - 97;
        int firstY = Integer.valueOf(line[0].charAt(1)) - 49;
        int secondX = line[1].codePointAt(0) - 97;
        int secondY = Integer.valueOf(line[1].charAt(1)) - 49;

        int[][] graph = new int[8][8];

        for (int i = 0; i < 8; ++i)
            for (int j = 0; j < 8; ++j)
                graph[i][j] = -1;

        graph[firstY][firstX] = 0;

        bfs(graph, firstX, firstY, 8);

        //выводи ответ
        if (graph[secondY][secondX] % 2 == 0)
        {
            System.out.println(graph[secondY][secondX] / 2);
        }
        else
            System.out.println(-1);

        /*for (int i = 0; i < 8; ++i)
        {
            for (int j = 0; j < 8; ++j)
                System.out.print(graph[i][j]);
            System.out.println();
        }*/

    }

    public static void bfs(int[][] graph, int firstX, int firstY, int N)
    {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {firstY, firstX});

        while (!queue.isEmpty())
        {
            int I = queue.get(0)[0];
            int J = queue.get(0)[1];
            queue.remove(0);

            //1
            if (I+1 < N && J-2 >= 0)
            {
                if (graph[I+1][J-2] == -1)
                {
                    graph[I+1][J-2] = graph[I][J] + 1;
                    queue.add(new int[] {I+1, J-2});
                }
            }
            //2
            if (I+2 < N && J-1 >= 0)
            {
                if (graph[I+2][J-1] == -1)
                {
                    graph[I+2][J-1] = graph[I][J] + 1;
                    queue.add(new int[] {I+2, J-1});
                }
            }
            //3
            if (I+2 < N && J+1 < N)
            {
                if (graph[I+2][J+1] == -1)
                {
                    graph[I+2][J+1] = graph[I][J] + 1;
                    queue.add(new int[] {I+2, J+1});
                }
            }
            //4
            if (I+1 < N && J+2 < N)
            {
                if (graph[I+1][J+2] == -1)
                {
                    graph[I+1][J+2] = graph[I][J] + 1;
                    queue.add(new int[] {I+1, J+2});
                }
            }
            //5
            if (I-1 >= 0 && J+2 < N)
            {
                if (graph[I-1][J+2] == -1)
                {
                    graph[I-1][J+2] = graph[I][J] + 1;
                    queue.add(new int[] {I-1, J+2});
                }
            }
            //6
            if (I-2 >= 0 && J+1 < N)
            {
                if (graph[I-2][J+1] == -1)
                {
                    graph[I-2][J+1] = graph[I][J] + 1;
                    queue.add(new int[] {I-2, J+1});
                }
            }
            //7
            if (I-2 >= 0 && J-1 >= 0)
            {
                if (graph[I-2][J-1] == -1)
                {
                    graph[I-2][J-1] = graph[I][J] + 1;
                    queue.add(new int[] {I-2, J-1});
                }
            }
            //8
            if (I-1 >= 0 && J-2 >= 0)
            {
                if (graph[I-1][J-2] == -1)
                {
                    graph[I-1][J-2] = graph[I][J] + 1;
                    queue.add(new int[] {I-1, J-2});
                }
            }
        }
    }

}
