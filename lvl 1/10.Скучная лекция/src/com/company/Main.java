package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 10.Скучная лекция - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();

        br.close();

        long[] array = new long[123];
        boolean unevenNumber;
        int len = str.length();

        if (len % 2 == 1)
        {
            unevenNumber = true;
        }
        else
        {
            unevenNumber = false;
        }

        for (int i = 1; i < len / 2 + 1; ++i)
        {

            array[str.codePointAt(i-1)] += i + i*(long)(len - i);

            array[str.codePointAt(len-i)] += i + i*(long)(len - i);

        }
        if (unevenNumber)
        {
            int localLen = len / 2 + 1;
            array[str.codePointAt( localLen - 1)] += localLen + localLen*(len - localLen);
        }
        for (int i = 97; i < 123; ++i)
        {
            if (array[i] > 0)
            {
                System.out.println((char)i + ": " + array[i]);
            }
        }
    }
}
