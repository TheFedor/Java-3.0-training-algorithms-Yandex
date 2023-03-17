package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 28.Космический мусорщик - lvl5 (Дивизион A)

        //long time = System.currentTimeMillis();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Записываем для каждой из возможных команд 0-5 ее подкоманду в виде массива чисел
        Map<Integer, int[]> map = new HashMap<>();
        String[] line;
        for (int i = 0; i < 6; ++i)
        {
            line = reader.readLine().split("");
            if (!line[0].equals(""))
            {
                map.put(i, new int[line.length]);
                for (int j = 0; j < line.length; ++j)
                {
                    switch (line[j])
                    {
                        case ("N"):
                            map.get(i)[j] = 0;
                            break;
                        case ("S"):
                            map.get(i)[j] = 1;
                            break;
                        case ("W"):
                            map.get(i)[j] = 2;
                            break;
                        case ("E"):
                            map.get(i)[j] = 3;
                            break;
                        case ("U"):
                            map.get(i)[j] = 4;
                            break;
                        case ("D"):
                            map.get(i)[j] = 5;
                            break;
                    }
                }
            }
            else
                map.put(i, new int[0]);
        }
        line = reader.readLine().split(" ");
        reader.close();

        int startCommand = 0;
        switch (line[0])
        {
            case ("N"):
                startCommand = 0;
                break;
            case ("S"):
                startCommand = 1;
                break;
            case ("W"):
                startCommand = 2;
                break;
            case ("E"):
                startCommand = 3;
                break;
            case ("U"):
                startCommand = 4;
                break;
            case ("D"):
                startCommand = 5;
                break;
        }
        int startNumber = Integer.valueOf(line[1]);

        int[][] dp = new int[startNumber][6];

        //заполняем первую строчку единицами
        for (int i = 0; i < 6; ++i)
            dp[0][i] = 1;

        //заполняем остальные строчки
        for (int i = 1; i < startNumber; ++i)
        {
            for (int j = 0; j < 6; ++j)
            {
                dp[i][j] = 1;
                for (int p = 0; p < map.get(j).length; ++p)
                {
                    dp[i][j] += dp[i-1][map.get(j)[p]];
                }
            }
        }

        System.out.println(dp[startNumber-1][startCommand]);

        //System.out.println("time: " + (System.currentTimeMillis()-time));






    }
}
