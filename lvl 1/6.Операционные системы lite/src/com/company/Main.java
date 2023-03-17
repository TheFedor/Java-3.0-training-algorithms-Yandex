package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // 6.Операционные системы lite - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int M = Integer.valueOf(br.readLine());
        int N = Integer.valueOf(br.readLine());

        int[][] arr = new int[N][3];

        String str;
        String[] strArray;
        int ai;
        int bi;

        for (int i = 0; i < N; ++i)
        {
            str = br.readLine();
            strArray = str.split(" ");
            ai = Integer.valueOf(strArray[0]);
            bi = Integer.valueOf(strArray[1]);

            arr[i][0] = ai; arr[i][1] = bi; arr[i][2] = i;
        }

        br.close();

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        NavigableSet<Integer> set = new TreeSet<>();
        Set<Integer> deleteSet = new HashSet<>();

        int c = 0;
        while (c < arr.length - 1)
        {
            if (arr[c][0] == arr[c+1][0])
            {
                int sch = c+2;
                set.add(c);
                set.add(c+1);
                while (sch < arr.length && arr[sch][0] == arr[c][0])
                {
                    set.add(sch);
                    sch++;
                }
                set.pollLast();
                deleteSet.addAll(set);
                set.clear();
            }
            int sch = c+1;
            while (sch < arr.length && arr[c][1] >= arr[sch][0] && arr[sch][0] != -1)
            {
                if (arr[c][2] < arr[sch][2])
                {
                    deleteSet.add(c);
                }
                else
                {
                    deleteSet.add(sch);
                }
                sch++;

            }

            c++;
        }

        System.out.println(N - deleteSet.size());
    }
}
