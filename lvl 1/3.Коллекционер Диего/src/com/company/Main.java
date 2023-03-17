package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
	// 3.Коллекционер диего - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine()); //число наклеек диего

        String line = br.readLine();
        String[] lineArray = line.split(" ");
        Set<Integer> set = new LinkedHashSet<>(N);
        for (int i = 0; i < N; ++i)
            set.add(Integer.valueOf(lineArray[i]));

        List<Integer> listDiego = new ArrayList<>(set.size());
        for (int obj : set)
            listDiego.add(obj);
        Collections.sort(listDiego);

        int K = Integer.valueOf(br.readLine()); //число коллекционеров

        line = br.readLine();
        br.close();
        lineArray = line.split(" ");

        List<Integer> answers = new ArrayList<>(K);
        int last1 = listDiego.size();

        for (int i = 0; i < K; ++i)
        {
            int collector = Integer.valueOf(lineArray[i]);
            int first = 0;
            int last = last1-1;
            int middle = (first + last)/2;

            while (first <= last)
            {
                if (listDiego.get(middle) > collector)
                {
                    last = middle-1;
                }
                else
                {
                    first = middle + 1;
                }
                middle = (first+last)/2;
            }

            if (first > 0 && listDiego.get(first-1) == collector)
                first--;
            answers.add(first);

        }
        for (Integer obj : answers)
            System.out.println(obj);


    }
}
