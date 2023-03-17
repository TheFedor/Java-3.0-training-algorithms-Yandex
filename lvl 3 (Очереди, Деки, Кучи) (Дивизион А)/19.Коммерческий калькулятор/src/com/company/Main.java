package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 19.Коммерческий калькулятор - lvl3 (Дивизион А)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        int[] numbersHeap = new int[N];
        int heapEnd = N;

        String line = reader.readLine();
        reader.close();
        String[] lineArray = line.split(" ");

        for (int i = 0; i < N; ++i)
            numbersHeap[i] = Integer.valueOf(lineArray[i]);

        //формируем правильную кучу
        for (int i = (N/2)-1; i >= 0; --i)
        {
            //просеиваем вниз
            int indexOfWorkNumber = i;
            while (2 * indexOfWorkNumber + 1 < heapEnd)
            {
                if (2 * indexOfWorkNumber + 2 < heapEnd)
                {
                    int indexOfMin = 2 * indexOfWorkNumber + 1;
                    if (numbersHeap[indexOfMin+1] < numbersHeap[indexOfMin])
                        indexOfMin++;

                    if (numbersHeap[indexOfWorkNumber] > numbersHeap[indexOfMin])
                    {
                        int parent = numbersHeap[indexOfWorkNumber];
                        numbersHeap[indexOfWorkNumber] = numbersHeap[indexOfMin];
                        numbersHeap[indexOfMin] = parent;
                        indexOfWorkNumber = indexOfMin;
                    }
                    else
                        break;
                }
                else
                {
                    int indexOfMin = 2 * indexOfWorkNumber + 1;
                    if (numbersHeap[indexOfWorkNumber] > numbersHeap[indexOfMin])
                    {
                        int parent = numbersHeap[indexOfWorkNumber];
                        numbersHeap[indexOfWorkNumber] = numbersHeap[indexOfMin];
                        numbersHeap[indexOfMin] = parent;
                        indexOfWorkNumber = indexOfMin;
                    }
                    else
                        break;
                }
            }
        }

        //теперь работаем решение
        //берем два минимальных из кучи, записываем их сумму в кучу
        //попутно прибавляем цену операции к ответу
        //          и так пока, после взятия двух минимальных, куча не станет пуста (heapEnd == 0)
        //          или пока после добавления суммы размер кучи не будет равен одному (heapEnd == 1)

        double price = 0d;

        do {

            int firstNumber = numbersHeap[0];
            //удаляем элемент из кучи просеиванием вниз
            numbersHeap[0] = numbersHeap[heapEnd-1];
            int indexOfWorkElement = 0;
            while (2 * indexOfWorkElement + 2 < heapEnd)
            {
                int indexOfMin = 2 * indexOfWorkElement + 1;
                if (numbersHeap[indexOfMin+1] < numbersHeap[indexOfMin])
                    indexOfMin++;

                if (numbersHeap[indexOfWorkElement] > numbersHeap[indexOfMin])
                {
                    int parent = numbersHeap[indexOfWorkElement];
                    numbersHeap[indexOfWorkElement] = numbersHeap[indexOfMin];
                    numbersHeap[indexOfMin] = parent;
                    indexOfWorkElement = indexOfMin;
                }
                else
                    break;
            }
            heapEnd--;

            int secondNumber = numbersHeap[0];
            //удаляем элемент из кучи просеиванием вниз
            numbersHeap[0] = numbersHeap[heapEnd-1];
            indexOfWorkElement = 0;
            while (2 * indexOfWorkElement + 2 < heapEnd)
            {
                int indexOfMin = 2 * indexOfWorkElement + 1;
                if (numbersHeap[indexOfMin+1] < numbersHeap[indexOfMin])
                    indexOfMin++;

                if (numbersHeap[indexOfWorkElement] > numbersHeap[indexOfMin])
                {
                    int parent = numbersHeap[indexOfWorkElement];
                    numbersHeap[indexOfWorkElement] = numbersHeap[indexOfMin];
                    numbersHeap[indexOfMin] = parent;
                    indexOfWorkElement = indexOfMin;
                }
                else
                    break;
            }
            heapEnd--;

            //прибавляем цену операции к ответу
            int resultOfSum = firstNumber + secondNumber;
            price += (double)resultOfSum * 0.05;

            //добавляем результат сложения в кучу просеиванием вверх
            numbersHeap[heapEnd] = resultOfSum;
            indexOfWorkElement = heapEnd;
            heapEnd++;
            while ((indexOfWorkElement-1)/2 >= 0)
            {
                int indexOfParent = (indexOfWorkElement-1)/2;

                if (numbersHeap[indexOfParent] > numbersHeap[indexOfWorkElement])
                {
                    int parent = numbersHeap[indexOfParent];
                    numbersHeap[indexOfParent] = numbersHeap[indexOfWorkElement];
                    numbersHeap[indexOfWorkElement] = parent;
                    indexOfWorkElement = indexOfParent;
                }
                else
                    break;
            }

        }
        while (heapEnd > 1);

        //выводим ответ (с двумя числами после запятой)
        String stringVersionOfPrice = String.format("%.2f", price);
        System.out.println(stringVersionOfPrice.replace((char)44, (char)46));

    }
}
