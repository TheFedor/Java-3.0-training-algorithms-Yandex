package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 16.Минимум на отрезке (этот вариант крайне неудачно вышел по времени исполнения)

        //long time = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] lineArray = line.split(" ");

        int n = Integer.valueOf(lineArray[0]);
        int k = Integer.valueOf(lineArray[1]);

        line = br.readLine();
        br.close();
        lineArray = line.split(" ");

        int[] queue = new int[n];
        int queueStart = 0;
        int queueEnd = 0;

        int[][] heap = new int[k][2];

        for (int i = 0; i < k; ++i)
        {
            heap[i][0] = Integer.valueOf(lineArray[i]);
            heap[i][1] = i;

            queue[queueEnd] = Integer.valueOf(lineArray[i]);
            queueEnd++;
        }

        //формируем правильную кучу
        for (int i = (k/2)-1; i>=0; --i)
        {
            int indexOfWorkElement = i;

            while (2 * indexOfWorkElement + 1 < k)
            {
                if (2 * indexOfWorkElement + 2 < k)
                {
                    int indOfMin = 2 * indexOfWorkElement + 1;
                    if (heap[indOfMin+1][0] < heap[indOfMin][0])
                        indOfMin++;

                    if (heap[indexOfWorkElement][0] > heap[indOfMin][0])
                    {
                        //свапаем
                        int parentValue = heap[indexOfWorkElement][0];
                        int parentInd = heap[indexOfWorkElement][1];
                        int childValue = heap[indOfMin][0];
                        int childInd = heap[indOfMin][1];
                        heap[indOfMin][0] = parentValue;
                        heap[indOfMin][1] = parentInd;
                        heap[indexOfWorkElement][0] = childValue;
                        heap[indexOfWorkElement][1] = childInd;
                        indexOfWorkElement = indOfMin;
                    }
                    else
                        break;
                }
                else
                {
                    int indOfMin = 2 * indexOfWorkElement + 1;

                    if (heap[indexOfWorkElement][0] > heap[indOfMin][0])
                    {
                        //свапаем
                        int parentValue = heap[indexOfWorkElement][0];
                        int parentInd = heap[indexOfWorkElement][1];
                        int childValue = heap[indOfMin][0];
                        int childInd = heap[indOfMin][1];
                        heap[indOfMin][0] = parentValue;
                        heap[indOfMin][1] = parentInd;
                        heap[indexOfWorkElement][0] = childValue;
                        heap[indexOfWorkElement][1] = childInd;
                        indexOfWorkElement = indOfMin;
                    }
                    else
                        break;
                }
            }
        }

        //теперь решаем
        StringBuilder answers = new StringBuilder("");

        for (int i = k; i < n; ++i)
        {
            //добавляем в ответы наименьший элемент окна
            answers.append(Integer.toString(heap[0][0])).append("\n");

            //удаляем из кучи элемент, вышедший из окна
            for (int j = 0; j < k; ++j)
            {
                if (heap[j][0] == queue[queueStart])
                {
                    if (heap[j][1] == queueStart)
                    {
                        //свапаем с последним и просеиваем вниз
                        int toSwapValue = heap[k-1][0];
                        int toSwapInd = heap[k-1][1];
                        heap[j][0] = toSwapValue;
                        heap[j][1] = toSwapInd;
                        int indexOfWorkElement = j;

                        while (2 * indexOfWorkElement + 1 < k)
                        {
                            if (2 * indexOfWorkElement + 2 < k)
                            {
                                int indOfMin = 2 * indexOfWorkElement + 1;
                                if (heap[indOfMin+1][0] < heap[indOfMin][0])
                                    indOfMin++;

                                if (heap[indexOfWorkElement][0] > heap[indOfMin][0])
                                {
                                    //свапаем
                                    int parentValue = heap[indexOfWorkElement][0];
                                    int parentInd = heap[indexOfWorkElement][1];
                                    int childValue = heap[indOfMin][0];
                                    int childInd = heap[indOfMin][1];

                                    heap[indOfMin][0] = parentValue;
                                    heap[indOfMin][1] = parentInd;
                                    heap[indexOfWorkElement][0] = childValue;
                                    heap[indexOfWorkElement][1] = childInd;

                                    indexOfWorkElement = indOfMin;
                                }
                                else
                                    break;
                            }
                            else
                            {
                                int indOfMin = 2 * indexOfWorkElement + 1;

                                if (heap[indexOfWorkElement][0] > heap[indOfMin][0])
                                {
                                    //свапаем
                                    int parentValue = heap[indexOfWorkElement][0];
                                    int parentInd = heap[indexOfWorkElement][1];
                                    int childValue = heap[indOfMin][0];
                                    int childInd = heap[indOfMin][1];

                                    heap[indOfMin][0] = parentValue;
                                    heap[indOfMin][1] = parentInd;
                                    heap[indexOfWorkElement][0] = childValue;
                                    heap[indexOfWorkElement][1] = childInd;

                                    indexOfWorkElement = indOfMin;
                                }
                                else
                                    break;
                            }
                        }
                    }
                }
            }
            queueStart++;

            //добавляем в кучу новый элемент, вошедший в сдвинутое окно
            queue[queueEnd] = Integer.valueOf(lineArray[i]);
            queueEnd++;

            heap[k-1][0] = Integer.valueOf(lineArray[i]);
            heap[k-1][1] = i;

            //просеиваем вверх
            int indexOfWorkElement = k-1;
            while ((indexOfWorkElement-1)/2 >= 0)
            {
                int parent = (indexOfWorkElement-1)/2;

                if (heap[parent][0] > heap[indexOfWorkElement][0])
                {
                    //свапаем
                    int parentValue = heap[parent][0];
                    int parentInd = heap[parent][1];
                    int childValue = heap[indexOfWorkElement][0];
                    int childInd = heap[indexOfWorkElement][1];

                    heap[parent][0] = childValue;
                    heap[parent][1] = childInd;
                    heap[indexOfWorkElement][0] = parentValue;
                    heap[indexOfWorkElement][1] = parentInd;

                    indexOfWorkElement = parent;
                }
                else
                    break;
            }


        }
        answers.append(Integer.toString(heap[0][0])).append("\n");
        System.out.println(answers);

        //System.out.println("time: " + (System.currentTimeMillis()-time));

    }
}
