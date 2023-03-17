package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
	// 33.Списывание

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");
        int N = Integer.valueOf(line[0]); //число вершин
        int M = Integer.valueOf(line[1]); //Число ребер

        ArrayList<Integer>[] graph = new ArrayList[N+1];
        for (int i = 1; i < N+1; ++i)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; ++i)
        {
            line = reader.readLine().split(" ");
            int first = Integer.valueOf(line[0]);
            int second = Integer.valueOf(line[1]);

            graph[first].add(second);
            graph[second].add(first);
        }

        boolean b = true; //true - двудольный, иначе - false
        int[] colour = new int[N+1]; //массив цветов вершин
        for (int i = 1; i < N+1; ++i) {
            if (colour[i] == 0) {
                b = dfs(graph, colour, i, b, 1);
            }
        }

        if (b)
            System.out.println("YES");
        else
            System.out.println("NO");

    }

    //по началу передаем [сам граф, цвета вершин, позицию сейчас, просто провер. переменную, цвет вершины (для начала всегда 1)]
    public static boolean dfs(ArrayList<Integer>[] graph, int[] colour, int now, boolean b, int oneColour) {
        colour[now] = oneColour;
        int nextColour = 1;
        if (oneColour == 1)
            nextColour = 2;
        for (int neig : graph[now])
        {
            if (colour[neig] == colour[now]) {
                b = false;
                break;
            }
            else
                if (colour[neig] == 0)
                    b = dfs(graph, colour, neig, b, nextColour);
        }
        return b;
    }
}
