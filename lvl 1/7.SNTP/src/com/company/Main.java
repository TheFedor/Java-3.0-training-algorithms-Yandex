package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 7.SNTP - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String[] strArray = str.split(":");
        int A = Integer.valueOf(strArray[0]) * 60 * 60;
        A += Integer.valueOf(strArray[1]) * 60;
        A += Integer.valueOf(strArray[2]);

        str = br.readLine();
        strArray = str.split(":");
        int B = Integer.valueOf(strArray[0]) * 60 * 60;
        B += Integer.valueOf(strArray[1]) * 60;
        B += Integer.valueOf(strArray[2]);

        str = br.readLine();
        strArray = str.split(":");
        int C = Integer.valueOf(strArray[0]) * 60 * 60;
        C += Integer.valueOf(strArray[1]) * 60;
        C += Integer.valueOf(strArray[2]);

        br.close();

        //решение

        //вычисляем, сколько времени добавить
        int time;

        if (A > C){
            if ((C + 86400 - A) % 2 == 1)
            {
                C++;
            }
            time = (C + 86400 - A) / 2;
        }
        else
        {
            if ((C - A) % 2 == 1)
            {
                C++;
            }
            time = (C - A) / 2;
        }

        //вычисляем получившееся время

        B += time;

        if (B >= 86400)
        {
            B -= 86400;
        }

        //переводим получившееся время в нужный формат вывода
        int h = 0;
        int m = 0;
        int s = 0;

        while (B - 3600 >= 0)
        {
            B -= 3600;
            h++;
        }
        while (B - 60 >= 0)
        {
            B -= 60;
            m++;
        }
        s = B;

        String strH;
        String strM;
        String strS;

        if ( h / 10 > 0)
        {
            strH = Integer.toString(h);
        }
        else
        {
            strH = "0" + Integer.toString(h);
        }
        if ( m / 10 > 0)
        {
            strM = Integer.toString(m);
        }
        else
        {
            strM = "0" + Integer.toString(m);
        }
        if (s / 10 > 0)
        {
            strS = Integer.toString(s);
        }
        else
        {
            strS = "0" + Integer.toString(s);
        }

        //вывод результата
        String result = String.join(":", strH, strM, strS);
        System.out.println(result);
    }
}
