package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 33.Радио Байтик - lvl6 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine()); //число вышек
        String[] line;

        //заводим массив координат вышек
        int[][] coordinates = new int[N][2];
        for (int i = 0; i < N; ++i)
        {
            line = reader.readLine().split(" ");
            coordinates[i][0] = Integer.valueOf(line[0]);
            coordinates[i][1] = Integer.valueOf(line[1]);
        }

        ArrayList<int[]>[] graph = new ArrayList[N]; // под индексом - номер вершины. В массиве: [distance, vertex]
        int[][] graph2 = new int[N][N]; //граф для поиска ответа в конце
        for (int i = 0; i < N; ++i)
            graph[i] = new ArrayList<>();

        //заполняем граф и попутно ищем минимальный и максимальный квадрат расстояния
        int minDistance = 100000000;
        int maxDistance = 0;
        for (int i = 0; i < N-1; ++i)
        {
            for (int j = i+1; j < N; ++j)
            {
                int distance = (int)(Math.pow(coordinates[i][0] - coordinates[j][0], 2) + Math.pow(coordinates[i][1] - coordinates[j][1], 2));
                graph[i].add(new int[] {distance, j});
                graph[j].add(new int[] {distance, i});
                if (distance > maxDistance)
                    maxDistance = distance;
                if (distance < minDistance)
                    minDistance = distance;
                graph2[i][j] = distance;
            }
        }

        //если все вышки находятся на одинаковом расстоянии от всех остальных вышек
        if (minDistance == maxDistance)
        {
            double answer = Math.sqrt(minDistance);
            answer = answer / (double)2;
            System.out.printf("%.9f\n",answer);
            for (int i = 0; i < N; ++i)
                System.out.print(1 + " ");
        }
        else
        {
            int middle = (minDistance + maxDistance) / 2;
            int[] colours = new int[N];
            while (minDistance + 1 != maxDistance)
            {
                Arrays.fill(colours, 0);
                boolean b = true;

                //тут делаем
                for (int i = 0; i < N; ++i)
                {
                    if (colours[i] == 0)
                    {
                        colours[i] = 1;
                        b = dfs(graph, colours, i, middle, b);
                    }

                    if (!b)
                        break;
                }


                //если получилось окрасить
                if (b)
                    minDistance = middle;
                else
                {
                    maxDistance = middle;
                    //удаляем все ненужные ребра
                    for (int i = 0; i < N; ++i)
                        for (int j = 0; j < graph[i].size(); ++j)
                        {
                            if (graph[i].get(j)[0] > middle)
                            {
                                graph[i].remove(j);
                                j--;
                            }
                        }
                }

                middle = (minDistance + maxDistance) / 2;

            }

            //пробуем окрасить при middle = maxDistance и если получается, то это ответ
            //если же не получается, то окрашиваем при middle = minDistance и выводим ответ
            Arrays.fill(colours, 0);
            boolean b = true;
            middle = maxDistance;
            for (int i = 0; i < N; ++i)
            {
                if (colours[i] == 0)
                {
                    colours[i] = 1;
                    b = dfs(graph, colours, i, middle, b);
                }

                if (!b)
                    break;
            }
            if (!b)
            {
                middle = minDistance;
                Arrays.fill(colours, 0);
                b = true;
                for (int i = 0; i < N; ++i)
                {
                    if (colours[i] == 0)
                    {
                        colours[i] = 1;
                        b = dfs(graph, colours, i, middle, b);
                    }

                    if (!b)
                        break;
                }
            }

            //ищем радиус действия радиопередатчика
            int dist = 1000000000;
            for (int i = 0; i < N-1; ++i)
                for (int j = i+1; j < N; ++j)
                    if (colours[i] == colours[j])
                        if (graph2[i][j] < dist)
                        {
                            dist = graph2[i][j];
                            //System.out.println("i: " + i);
                            //System.out.println("j: " + j);
                        }

           // System.out.println("dist: " + dist);

            double answer = Math.sqrt(dist);
            answer = answer / (double)2;
            System.out.printf("%.9f\n",answer);


            for (int i = 0; i < N; ++i)
                System.out.print(colours[i] + " ");
            /*for (int i = 0; i < N; ++i)
            {
                for (int[] obj : graph[i])
                {
                    System.out.print(obj[0] + "-" + obj[1] + "  ");
                }
                System.out.println();
            }*/

        }

    }

    public static boolean dfs(ArrayList<int[]>[] graph, int[] colours, int now, int middle, boolean b)
    {
        for (int[] neig : graph[now])
        {
            if (neig[0] <= middle)
            {
                if (colours[now] == colours[neig[1]])
                {
                    b = false;
                    break;
                }
                else if(colours[neig[1]] == 0)
                {
                    colours[neig[1]] = 3 - colours[now];
                    b = dfs(graph, colours, neig[1], middle, b);
                }
            }
            if (!b)
                break;
        }
        return b;
    }

}
