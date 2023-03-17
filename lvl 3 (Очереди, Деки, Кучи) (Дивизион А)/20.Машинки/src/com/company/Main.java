package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 20.Машинки - lvl3 - (Дивизион А)

       // long time = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String line = reader.readLine();
        String[] lineArray = line.split(" ");

        int N = Integer.valueOf(lineArray[0]); //число машинок
        int K = Integer.valueOf(lineArray[1]); //макс. машинок на полу
        int P = Integer.valueOf(lineArray[2]); //число смен интересующей Петю машинки

        Map<Integer, LinkedList<Integer>> priorityOfCars = new HashMap<>(N); // [машинка:на каких индексах встречается]
        int[] cars = new int[P];

        for (int i = 0; i < P; ++i)
        {
            int car = Integer.valueOf(reader.readLine());
            if (!priorityOfCars.containsKey(car))
            {
                priorityOfCars.put(car, new LinkedList<>());
                priorityOfCars.get(car).add(i);
            }
            else
            {
                priorityOfCars.get(car).add(i);
            }
            cars[i] = car;
        }
        reader.close();

        int numberOfOperations = 0;

        int k1;
        if (K % 2 == 0)
            k1 = K + 1;
        else
            k1 = K;
        int[][] heap = new int[k1][2]; //[приоритет][машинка]
        int heapEnd = 0;
        Map<Integer, Integer> dictionary = new HashMap<>();

        int maxPriority = P + 1;

        if (priorityOfCars.size() <= K)
            System.out.println(priorityOfCars.size());
        else
        {

            for (int i = 0; i < P; ++i)
            {
                int car = cars[i];
                int carPriority; //чем больше приоритет - тем ближе машинка к удалению
                if (priorityOfCars.get(car).size() == 1)
                {
                    carPriority = maxPriority;
                    priorityOfCars.remove(car);
                }
                else
                {
                    priorityOfCars.get(car).remove(0); //[машинка:приоритет]
                    carPriority = priorityOfCars.get(car).get(0);
                }

                if (heapEnd == K)
                {
                    if (dictionary.containsKey(car))
                    {
                        heap[dictionary.get(car)][0] = carPriority;
                        int indexToDictionary = dictionary.get(car);
                        //просеиваем вверх
                        while ((indexToDictionary-1)/2 >= 0)
                        {
                            if (heap[(indexToDictionary-1)/2][0] < heap[indexToDictionary][0])
                            {
                                dictionary.put(heap[(indexToDictionary-1)/2][1], indexToDictionary);
                                int parentPriority = heap[(indexToDictionary-1)/2][0];
                                int parentCar = heap[(indexToDictionary-1)/2][1];
                                heap[(indexToDictionary-1)/2][0] = heap[indexToDictionary][0];
                                heap[(indexToDictionary-1)/2][1] = heap[indexToDictionary][1];
                                heap[indexToDictionary][0] = parentPriority;
                                heap[indexToDictionary][1] = parentCar;
                                indexToDictionary = (indexToDictionary-1)/2;
                            }
                            else
                                break;
                        }
                        dictionary.put(car, indexToDictionary);
                    }
                    else
                    {
                        numberOfOperations++;
                        dictionary.remove(heap[0][1]);
                        heap[0][0] = carPriority;
                        heap[0][1] = car;
                        int indexToDictionary = 0;
                        //просеиваем вниз
                        while (2 * indexToDictionary + 2 < heapEnd)
                        {
                            int indOfMax = 2 * indexToDictionary + 1;
                            if (heap[indOfMax+1][0] > heap[indOfMax][0])
                                indOfMax++;

                            if (heap[indexToDictionary][0] < heap[indOfMax][0])
                            {
                                dictionary.put(heap[indOfMax][1], indexToDictionary);
                                int parentPriority = heap[indexToDictionary][0];
                                int parentCar = heap[indexToDictionary][1];
                                heap[indexToDictionary][0] = heap[indOfMax][0];
                                heap[indexToDictionary][1] = heap[indOfMax][1];
                                heap[indOfMax][0] = parentPriority;
                                heap[indOfMax][1] = parentCar;
                                indexToDictionary = indOfMax;
                            }
                            else
                                break;
                        }
                        dictionary.put(car, indexToDictionary);
                    }
                }
                else
                {
                    if (dictionary.containsKey(car))
                    {
                        heap[dictionary.get(car)][0] = carPriority;
                        int indexToDictionary = dictionary.get(car);
                        //просеиваем вверх
                        while ((indexToDictionary-1)/2 >= 0)
                        {
                            if (heap[(indexToDictionary-1)/2][0] < heap[indexToDictionary][0])
                            {
                                dictionary.put(heap[(indexToDictionary-1)/2][1], indexToDictionary);
                                int parentPriority = heap[(indexToDictionary-1)/2][0];
                                int parentCar = heap[(indexToDictionary-1)/2][1];
                                heap[(indexToDictionary-1)/2][0] = heap[indexToDictionary][0];
                                heap[(indexToDictionary-1)/2][1] = heap[indexToDictionary][1];
                                heap[indexToDictionary][0] = parentPriority;
                                heap[indexToDictionary][1] = parentCar;
                                indexToDictionary = (indexToDictionary-1)/2;
                            }
                            else
                                break;
                        }
                        dictionary.put(car, indexToDictionary);
                    }
                    else
                    {
                        numberOfOperations++;
                        heap[heapEnd][1] = car;
                        heap[heapEnd][0] = carPriority;
                        int indexToDictionary = heapEnd;
                        heapEnd++;
                        //просеиваем вверх
                        while ((indexToDictionary-1)/2 >= 0)
                        {
                            if (heap[(indexToDictionary-1)/2][0] < heap[indexToDictionary][0])
                            {
                                dictionary.put(heap[(indexToDictionary-1)/2][1], indexToDictionary);
                                int parentPriority = heap[(indexToDictionary-1)/2][0];
                                int parentCar = heap[(indexToDictionary-1)/2][1];
                                heap[(indexToDictionary-1)/2][0] = heap[indexToDictionary][0];
                                heap[(indexToDictionary-1)/2][1] = heap[indexToDictionary][1];
                                heap[indexToDictionary][0] = parentPriority;
                                heap[indexToDictionary][1] = parentCar;
                                indexToDictionary = (indexToDictionary-1)/2;
                            }
                            else
                                break;
                        }
                        dictionary.put(car, indexToDictionary);

                        if (heapEnd == K && K % 2 == 0) //это для того, чтобы в заполненной куче никогда не было одного потомка (упрощает просеивание вниз)
                        {
                            K++;
                            heap[heapEnd][0] = -1;
                            heap[heapEnd][1] = -1;
                            heapEnd++;
                        }
                    }

                }

            }

            System.out.println(numberOfOperations);
        }


        //System.out.println("time: " + (System.currentTimeMillis()-time));


    }
}
