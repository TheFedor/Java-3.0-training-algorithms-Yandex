package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        //8. Минимальный прямоугольник - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.valueOf(br.readLine());

        String line;

        int up;
        int down;
        int left;
        int right;

        line = br.readLine();
        String[] arrayLine = line.split(" ");

        left = Integer.valueOf(arrayLine[0]);
        right = left;
        up = Integer.valueOf(arrayLine[1]);
        down = up;


        for (int i = 0; i < k-1; ++i)
        {
            line = br.readLine();
            arrayLine = line.split(" ");

            if (Integer.valueOf(arrayLine[0]) > right)
            {
                right = Integer.valueOf(arrayLine[0]);
            }
            else if (Integer.valueOf(arrayLine[0]) < left)
            {
                left = Integer.valueOf(arrayLine[0]);
            }

            if (Integer.valueOf(arrayLine[1]) > up)
            {
                up = Integer.valueOf(arrayLine[1]);
            }
            else if(Integer.valueOf(arrayLine[1]) < down)
            {
                down = Integer.valueOf(arrayLine[1]);
            }
        }

        br.close();

        System.out.println(left + " " + down + " " + right + " " + up);
    }
}
