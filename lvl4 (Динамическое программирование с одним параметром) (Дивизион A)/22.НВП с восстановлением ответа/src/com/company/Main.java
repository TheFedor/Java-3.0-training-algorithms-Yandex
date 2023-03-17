package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// 22.НВП с восстановлением ответа

        //long time = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(reader.readLine());

        int[] numbers = new int[N]; //тут храним сами числа
        int[] fromIndex = new int[N]; //тут будем хранить индексы прошлых элементов подпоследовательностей
        int[] sequenceLength = new int[N]; //тут будем для соответствующего числа писать максимальную длину подпоследовательности до этого числа включительно
        int indexMaxSequence = 0; //тут храним индекс конца самой большой подпоследовательности
        int sizeMaxSequence = 0;

        String line = reader.readLine();
        reader.close();
        String[] lineArray = line.split(" ");
        for (int i = 0; i < N; ++i)
             numbers[i] = Integer.valueOf(lineArray[i]);
        Arrays.fill(sequenceLength, 1);
        Arrays.fill(fromIndex, -1);

        for (int i = 0; i < N; ++i)
        {
            if (sequenceLength[i] > sizeMaxSequence)
            {
                sizeMaxSequence = sequenceLength[i];
                indexMaxSequence = i;
            }
            for (int j = i + 1; j < N; ++j)
            {
                if (numbers[j] > numbers[i])
                {
                    if (sequenceLength[j] < sequenceLength[i]+1)
                    {
                        sequenceLength[j] = sequenceLength[i]+1;
                        fromIndex[j] = i;
                    }
                }
            }
        }

        List<Integer> theBiggestSequence = new ArrayList<>();

        while (indexMaxSequence != -1)
        {
            theBiggestSequence.add(numbers[indexMaxSequence]);
            indexMaxSequence = fromIndex[indexMaxSequence];
        }

        for (int i = theBiggestSequence.size()-1; i >= 0; --i)
            System.out.print(theBiggestSequence.get(i) + " ");
        System.out.println();

        //System.out.println("time: " + (System.currentTimeMillis()-time));

    }
}
