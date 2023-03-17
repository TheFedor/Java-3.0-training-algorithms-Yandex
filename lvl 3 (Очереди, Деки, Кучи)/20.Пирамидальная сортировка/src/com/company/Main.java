package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
	// 20.Пирамидальная сортировка - lvl3

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine()); //число элементов массива

        int[] array = new int[N];

        String line = br.readLine();
        br.close();
        String[] lineArray = line.split(" ");

        for (int i = 0; i < N; ++i)
        {
            array[i] = Integer.valueOf(lineArray[i]);
        }

        //переводим массив в кучу
        for (int i = array.length/2-1; i >= 0; --i)
        {
            //просеиваем вниз
            int indexOfWorkElement = i;
            while (2 * indexOfWorkElement + 1 < array.length)
            {
                if (2 * indexOfWorkElement + 2 < array.length)
                {
                    int indexOfMax = 2 * indexOfWorkElement + 2;
                    if (array[indexOfMax-1] > array[indexOfMax])
                        indexOfMax--;

                    if (array[indexOfWorkElement] < array[indexOfMax])
                    {
                        int parent = array[indexOfWorkElement];
                        array[indexOfWorkElement] = array[indexOfMax];
                        array[indexOfMax] = parent;
                        indexOfWorkElement = indexOfMax;
                    }
                    else
                    {
                        break;
                    }
                }
                else
                {
                    //проверяем, меньше ли левого и, если да, свапаем
                    if (array[indexOfWorkElement] < array[2 * indexOfWorkElement + 1])
                    {
                        int parent = array[indexOfWorkElement];
                        array[indexOfWorkElement] = array[2 * indexOfWorkElement + 1];
                        array[2 * indexOfWorkElement + 1] = parent;
                        indexOfWorkElement = 2 * indexOfWorkElement + 1;
                    }
                    else
                    {
                        break;
                    }
                }
            }

        }

        //проводим пирамидальную сортировку
        int indEndHeap = array.length-1;
        while (indEndHeap > 0)
        {
            int toArray = array[0];
            array[0] = array[indEndHeap];

            int indexOfWorkElement = 0;
            while (2 * indexOfWorkElement + 2 <= indEndHeap)
            {
                int indMax = 2 * indexOfWorkElement + 2;
                if (array[indMax - 1] > array[indMax])
                    indMax--;

                if (array[indMax] > array[indexOfWorkElement])
                {
                    int parent = array[indexOfWorkElement];
                    array[indexOfWorkElement] = array[indMax];
                    array[indMax] = parent;
                    indexOfWorkElement = indMax;
                }
                else
                {
                    break;
                }
            }

            array[indEndHeap] = toArray;
            indEndHeap--;
        }

        for (int number : array)
            System.out.print(number + " ");

    }
}
