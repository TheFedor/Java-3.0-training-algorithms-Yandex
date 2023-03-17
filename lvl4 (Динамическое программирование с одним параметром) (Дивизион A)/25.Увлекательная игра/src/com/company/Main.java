package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        // 25.Увлекательная игра - lvl4 (Дивизион А)

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        reader.close();
        String[] lineArray = line.split(" ");

        int n = Integer.valueOf(lineArray[0]);
        int a = Integer.valueOf(lineArray[1]);
        int b = Integer.valueOf(lineArray[2]);

        if (n > 1) {
            if (a == 0 || b == 0) {
                if (a > b)
                    System.out.println(a);
                else
                    System.out.println(b);
            } else if (a == b) {
                int two = 2;
                int count = 1;
                while (two < n) {
                    two *= 2;
                    count++;
                }
                System.out.println(count * a);

            } else {

                int[] array = new int[n+1+5];
                array[2] = Math.max(a, b);
                int max = Math.max(a,b);
                int min = Math.min(a,b);

                for (int i = 3; i <= n+5; ++i)
                {
                    int c = 100000000;
                    for (int j = 1; j < i; ++j)
                    {
                        if ((max + array[j] > min + array[i-j]) && (max + array[j] < c))
                            c = max + array[j];
                        if ((max + array[j] <= min + array[i-j]) && (min + array[i-j] < c))
                            c = min + array[i-j];
                    }
                    array[i] = c;
                }
                System.out.println(array[n]);

            }
        } else
            System.out.println(0);


    }
}
