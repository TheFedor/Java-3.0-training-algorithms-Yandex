package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 18.Тупики - lvl3 (Дивизион А) ...работает хорошо, но на упоре времени...

        long time = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));

        String line = br.readLine();
        String[] lineArray = line.split(" ");

        int K = Integer.valueOf(lineArray[0]); //число тупиков
        int N = Integer.valueOf(lineArray[1]); //число электричек

        StringBuilder answ = new StringBuilder();
        //int[] answer = new int[N];
        int numbersOfParking = 0;
        NavigableMap<Integer, List<Integer>> parkingSpacec = new TreeMap<>(); //[время отправления : номера электричек]
        Map<Integer, Integer> TrainParkingNumber = new HashMap<>();//[номер электрички: тупик]
        //boolean[] b = new boolean[K];//занят ли тупик (true - занят)
        NavigableSet<Integer> freeSpases = new TreeSet<>(); //[индекс незанятого]
        for (int i = 0; i < K; ++i)
            freeSpases.add(i);
        //Set<Boolean> b = new HashSet<>();
        int k1 = 0;
        boolean tr = true;

        int[][] trains = new int[N][2]; //[прибытие;отправление]
        for (int i = 0; i < N; ++i)
        {
            line = br.readLine();
            lineArray = line.split(" ");
            trains[i][0] = Integer.valueOf(lineArray[0]);
            trains[i][1] = Integer.valueOf(lineArray[1]);

            if (!parkingSpacec.isEmpty() && (parkingSpacec.firstKey() < trains[i][0]))
            {
                while (!parkingSpacec.isEmpty() && parkingSpacec.firstKey() < trains[i][0])
                {
                    for (int j = 0; j < parkingSpacec.get(parkingSpacec.firstKey()).size(); ++j)
                    {
                        //b[TrainParkingNumber.get(parkingSpacec.get(parkingSpacec.firstKey()).get(j))] = false;
                        freeSpases.add(TrainParkingNumber.get(parkingSpacec.get(parkingSpacec.firstKey()).get(j)));
                        TrainParkingNumber.remove(parkingSpacec.get(parkingSpacec.firstKey()).get(j));
                        k1--;
                    }
                    parkingSpacec.remove(parkingSpacec.firstKey());
                }
            }
            if (k1 + 1 <= K)
            {
                //добавляем
                int l = freeSpases.first();
                freeSpases.remove(freeSpases.first());
                //b[l] = true;
                k1++;
                //answer[i] = l+1;
                answ.append(l+1).append("\n");
                TrainParkingNumber.put(i, l);
                if (parkingSpacec.containsKey(trains[i][1]))
                {
                    parkingSpacec.get(trains[i][1]).add(i);
                }
                else
                {
                    parkingSpacec.put(trains[i][1], new ArrayList<>());
                    parkingSpacec.get(trains[i][1]).add(i);
                }
            }
            else
            {
                System.out.println(0 + " " + (i+1));
                tr = false;
                break;
                //ошибку выводим
            }
            //уезжают в то же время
            if (parkingSpacec.containsKey(trains[i][0]))
            {
                for (int j = 0; j < parkingSpacec.get(trains[i][0]).size(); ++j)
                {
                    //b[TrainParkingNumber.get(parkingSpacec.get(trains[i][0]).get(j))] = false;
                    freeSpases.add(TrainParkingNumber.get(parkingSpacec.get(trains[i][0]).get(j)));
                    TrainParkingNumber.remove(parkingSpacec.get(trains[i][0]).get(j));
                    k1--;
                }
                parkingSpacec.remove(trains[i][0]);
            }

            //System.out.println("time:№trains: " + parkingSpacec);
            //System.out.println("№train:space: " + TrainParkingNumber);
            //System.out.println("spaces: " + Arrays.toString(b));



        }
        br.close();

        if (tr)
        {
            //выводим ответ
            answ.setLength(answ.length()-1);
            System.out.println(answ);
        }

        System.out.println("time: " + (System.currentTimeMillis()-time));

    }
}
