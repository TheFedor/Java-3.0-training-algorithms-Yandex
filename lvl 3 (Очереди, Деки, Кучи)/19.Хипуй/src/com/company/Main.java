package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 19.Хипуем - lvl3

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine()); //число команд

        int[] heap = new int[131071];
        int indexOfHeapEnd = 0;

        String line;
        String[] lineArray;

        for (int i = 0; i < N; ++i)
        {
            line = br.readLine();
            lineArray = line.split(" ");

            if (lineArray[0].equals("0")) //если добавляем
            {
                heap[indexOfHeapEnd] = Integer.valueOf(lineArray[1]);
                indexOfHeapEnd++;

                //просеивание вверх
                int indexOfNewElement = indexOfHeapEnd - 1;//индекс просеиваемого элемента

                while (indexOfNewElement > 0 && (heap[(indexOfNewElement-1)/2] < heap[indexOfNewElement]))
                {
                    //свапаем
                    int parent = heap[(indexOfNewElement-1)/2];
                    heap[(indexOfNewElement-1)/2] = heap[indexOfNewElement];
                    heap[indexOfNewElement] = parent;
                    indexOfNewElement = (indexOfNewElement-1)/2;
                }

            }
            else //если забираем наибольшее
            {
                System.out.println(heap[0]);
                indexOfHeapEnd--;
                heap[0] = heap[indexOfHeapEnd];
                //просеивание вниз

                int indexOfWorkElement = 0; //индекс просеиваемого элемента
                while (2 * indexOfWorkElement + 1 < indexOfHeapEnd)
                {
                    if (2 * indexOfWorkElement + 2 < indexOfHeapEnd)
                    {
                        //просматриваем оба потомка
                        if ((heap[2 * indexOfWorkElement + 1] > heap[2 * indexOfWorkElement + 2]))
                        {
                            //свапаем с левым, если мы меньше
                            if (heap[indexOfWorkElement] < heap[2 * indexOfWorkElement + 1])
                            {
                                int parent = heap[indexOfWorkElement];
                                heap[indexOfWorkElement] = heap[2 * indexOfWorkElement + 1];
                                heap[2 * indexOfWorkElement + 1] = parent;
                                indexOfWorkElement = 2 * indexOfWorkElement + 1;
                            }
                            else
                            {
                                break;
                            }
                        }
                        else
                        {
                            //свапаем с правым, если мы меньше
                            if (heap[indexOfWorkElement] < heap[2 * indexOfWorkElement + 2])
                            {
                                int parent = heap[indexOfWorkElement];
                                heap[indexOfWorkElement] = heap[2 * indexOfWorkElement + 2];
                                heap[2 * indexOfWorkElement + 2] = parent;
                                indexOfWorkElement = 2 * indexOfWorkElement + 2;
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    else
                    {
                        //просматриваем только левого потомка
                        if (heap[2 * indexOfWorkElement + 1] > heap[indexOfWorkElement])
                        {
                            //свапаем
                            int parent = heap[indexOfWorkElement];
                            heap[indexOfWorkElement] = heap[2 * indexOfWorkElement + 1];
                            heap[2 * indexOfWorkElement + 1] = parent;
                            indexOfWorkElement = 2 * indexOfWorkElement + 1;
                        }
                        else
                        {
                            break;
                        }
                    }




                }
            }
        }

        br.close();

    }
}
