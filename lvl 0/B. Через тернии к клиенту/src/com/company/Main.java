package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, TreeSet<Integer>> map = new TreeMap<>();
        //id, время, операция

        int N = Integer.valueOf(br.readLine());

        for (int i = 0; i < N; ++i)
        {
            String str = br.readLine();
            String[] babyMama = str.split(" ");

            int time = Integer.valueOf(babyMama[0]) * 24 * 60;
            time += Integer.valueOf(babyMama[1]) * 60;
            time += Integer.valueOf(babyMama[2]);

            int id = Integer.valueOf(babyMama[3]);

            boolean status;

            if (babyMama[4].equals("B"))
            {
                status = false;
            }
            else
            {
                status = true;
            }



            if (map.get(id) == null)
            {
                map.put(id, new TreeSet<>());
            }

            if (status)
            {
                map.get(id).add(time);
            }

        }

        br.close();

        for (int key : map.keySet())
        {
            int result = 0;
            boolean c = true;
            for (int obj : map.get(key))
            {
                if (c)
                {
                    result -= obj;
                    c = false;
                }
                else
                {
                    result += obj;
                    c = true;
                }

            }

            System.out.print(result + " ");

        }

    }

}

