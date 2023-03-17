package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 16.Минимум на отрезке (на мапах) - lvl3 (Дивизион А)

        //long time = System.currentTimeMillis();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        String[] lineArray = line.split(" ");

        int n = Integer.valueOf(lineArray[0]);
        int k = Integer.valueOf(lineArray[1]);

        NavigableMap<Integer, Integer> map = new TreeMap<>();
        int[] queue = new int[n];
        int queueStart = 0;
        int queueEnd = 0;

        line = br.readLine();
        br.close();
        lineArray = line.split(" ");

        for (int i = 0; i < k; ++i)
        {
            map.put(Integer.valueOf(lineArray[i]), i);
            queue[queueEnd] = Integer.valueOf(lineArray[i]);
            queueEnd++;
        }

        StringBuilder answer = new StringBuilder("");

        for (int i = k; i < n; ++i)
        {
            answer.append(map.firstKey()).append("\n");
            map.remove(queue[queueStart], queueStart);
            queueStart++;
            map.put(Integer.valueOf(lineArray[i]), i);
            queue[queueEnd] = Integer.valueOf(lineArray[i]);
            queueEnd++;

        }
        answer.append(map.firstKey());

        System.out.println(answer);

        //System.out.println("time: " + (System.currentTimeMillis()-time));
    }
}
