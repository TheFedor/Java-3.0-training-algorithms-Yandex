package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 22.Кузнечик - lvl4

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        br.close();
        String[] lineArray = line.split(" ");

        int N = Integer.valueOf(lineArray[0]); //число клеток
        int K = Integer.valueOf(lineArray[1]); //размер прыжка

        int[] array = new int[N+K+1];
        array[0] = 1;

        int sch = 0;

        while (sch < N-1)
        {
            for (int i = sch+1; i < sch+1+K; ++i)
                array[i] += array[sch];
            sch++;
        }

        System.out.println(array[N-1]);



    }
}
