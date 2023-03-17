package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
	// 2.Красивая строка - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.valueOf(br.readLine());
        String line = br.readLine();
        br.close();

        int start;
        int end;
        int max = 1;
        int k1;
        //решение

        for (int i = 97; i < 123; ++i) //проходим по всему алфавиту
        {
            start = 0;
            end = -1;
            k1 = k;
            for (int j = 0; j < line.length(); ++j)
            {
                if (line.codePointAt(j) != i)
                    k1--;
                end++;
                if (k1 < 0)
                {
                    k1 = 0;
                    end--;
                    break;
                }
            }
            if (end - start + 1 > max)
            {
                max = end - start + 1;
            }

            while (start < line.length())
            {
                start++;
                if (line.codePointAt(start-1) != i)
                {
                    k1++;
                    for (int j = end+1; j < line.length(); ++j)
                    {
                        if (line.codePointAt(j) != i)
                            k1--;
                        end++;
                        if (k1 < 0)
                        {
                            k1 = 0;
                            end--;
                            break;
                        }
                    }
                    if (end - start + 1 > max)
                    {
                        max = end - start + 1;
                    }
                }
            }
        }

        if (line.length() > 0)
            System.out.println(max);
        else
            System.out.println(0);


    }
}
