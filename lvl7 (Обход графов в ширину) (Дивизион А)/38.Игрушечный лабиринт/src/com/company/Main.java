package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
	// 38.Игрушечный лабиринт - lvl7 (Дивизион A)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] line = reader.readLine().split(" ");

        int N = Integer.valueOf(line[0]); //число строк
        int M = Integer.valueOf(line[1]); //число столбцов

        int[][] maze = new int[N+2][M+2];
        ArrayList<int[]> holes = new ArrayList<>();

        //заполняем лабиринт и окружем его стенками
        for (int i = 0; i < N+2; ++i)
        {
            maze[i][0] = -2;
            maze[i][M+1] = -2;
        }
        for (int j = 0; j < M+2; ++j)
        {
            maze[0][j] = -2;
            maze[N+1][j] = -2;
        }
        for (int i = 1; i < N+1; ++i)
        {
            line = reader.readLine().split(" ");
            for (int j = 1; j < M+1; ++j)
            {
                switch (line[j-1])
                {
                    case ("1"):
                        maze[i][j] = -2;
                        break;
                    case ("2"):
                        maze[i][j] = -3;
                        holes.add(new int[] {i, j});
                        break;
                    default:
                        maze[i][j] = -1;
                        break;
                }
            }
        }

        maze[1][1] = 0;
        //ищем ответ
        bfs(maze, N, M);

        //ищем кратчайший путь
        int answer = 0;
        for (int[] hole : holes)
        {
            if (maze[hole[0]][hole[1]] > -3)
            {
                answer = maze[hole[0]][hole[1]];
                break;
            }
        }

        System.out.println(answer);


    }

    public static void bfs(int[][] maze, int N, int M)
    {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 1});
        boolean b = true; //true - выход еще не найден

        while (!queue.isEmpty() && b)
        {
            int I = queue.get(0)[0];
            int J = queue.get(0)[1];
            queue.remove(0);

            //смотрим в 4 стороны

            //вправо
            for (int j = J+1; j < M+2; ++j)
            {
                if (maze[I][j] == -2)
                {
                    if (maze[I][j-1] == -1)
                    {
                        queue.add(new int[] {I, j-1});
                        maze[I][j-1] = maze[I][J]+1;
                    }
                    break;
                }
                if (maze[I][j] == -3)
                {
                    maze[I][j] = maze[I][J] + 1;
                    b = false;
                    break;
                }

            }

            //влево
            for (int j = J-1; j >=0; --j)
            {
                if (maze[I][j] == -2)
                {
                    if (maze[I][j+1] == -1)
                    {
                        queue.add(new int[] {I, j+1});
                        maze[I][j+1] = maze[I][J]+1;
                    }
                    break;
                }
                if (maze[I][j] == -3)
                {
                    maze[I][j] = maze[I][J] + 1;
                    b = false;
                    break;
                }
            }

            //вверх
            for (int i = I-1; i >= 0; --i)
            {
                if (maze[i][J] == -2)
                {
                    if (maze[i+1][J] == -1)
                    {
                        queue.add(new int[] {i+1, J});
                        maze[i+1][J] = maze[I][J] + 1;
                    }
                    break;
                }
                if (maze[i][J] == -3)
                {
                    maze[i][J] = maze[I][J] + 1;
                    b = false;
                    break;
                }
            }

            //вниз
            for (int i = I+1; i < N+2; ++i)
            {
                if (maze[i][J] == -2)
                {
                    if (maze[i-1][J] == -1)
                    {
                        queue.add(new int[] {i-1, J});
                        maze[i-1][J] = maze[I][J] + 1;
                    }
                    break;
                }
                if (maze[i][J] == -3)
                {
                    maze[i][J] = maze[I][J] + 1;
                    b = false;
                    break;
                }
            }
            /*
            System.out.println();
            for (int i = 0; i < N+2; ++i)
            {
                for (int j = 0; j < M+2; ++j)
                {
                    if (maze[i][j] == -2)
                        System.out.print("s ");
                    else if (maze[i][j] == -3)
                        System.out.print("d ");
                    else if (maze[i][j] == -1)
                        System.out.print("- ");
                    else
                        System.out.print(maze[i][j] + " ");
                }
                System.out.println();
            }

             */

        }
    }

}
