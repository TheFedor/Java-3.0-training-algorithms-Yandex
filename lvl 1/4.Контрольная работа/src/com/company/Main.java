package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // 4.Контрольная работа - lvl1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(br.readLine()); //число учеников
        int K = Integer.valueOf(br.readLine()); //число вариантов
        int row = Integer.valueOf(br.readLine()); //Ряд пети
        int oneOrTwo = Integer.valueOf(br.readLine()); //Вариант пети

        //Ряд и место пети: array[(row * 2 - 1) - 1] - если oneOrTwo = 1
        //array[row * 2 - 1] - если oneOrTwo = 2

        //вариант пети
        int indPetya;
        if (oneOrTwo == 1)
        {
            indPetya = (row * 2 - 1) - 1;
        }
        else
        {
            indPetya = (row * 2 - 1);
        }

        int result1 = -1;
        int result2 = -1;
        //проверяем места вверх
        if (indPetya - K >= 0)
        {
            result1 = indPetya - K;
        }

        //проверяем места вниз
        if (indPetya + K < N)
        {
            result2 = indPetya + K;
        }

        //выводим result
        if (result1 < 0 && result2 < 0)
        {
            System.out.println(-1);
        }
        else if (result1 < 0)
        {
            int variant;
            int ryad;
            if (result2 % 2 == 0)
            {
                variant = 1;
                ryad = (result2 + 2) / 2;
            }
            else
            {
                variant = 2;
                ryad = (result2 + 1) / 2;
            }

            System.out.println(ryad + " " + variant);
        }
        else if (result2 < 0)
        {
            int variant;
            int ryad;
            if (result1 % 2 == 0)
            {
                variant = 1;
                ryad = (result1 + 2) / 2;
            }
            else
            {
                variant = 2;
                ryad = (result1 + 1) / 2;
            }

            System.out.println(ryad + " " + variant);
        }
        else
        {
            //считаем длину векторов
            int vec1;
            int vec2;
            //равны - result2, кто меньше - того и result
            int xP; int yP;
            int xR1; int yR1;
            int xR2; int yR2;
            if (oneOrTwo == 1) { xP = 1; }
            else { xP = 0; }
            if (result1 % 2 == 0) { xR1 = 1; }
            else { xR1 = 0; }
            if (result2 % 2 == 0) { xR2 = 1; }
            else { xR2 = 0; }
            if (N % 2 == 0) {
                yP = (N - 1 - indPetya) / 2;
                yR1 = (N - 1 - result1) / 2;
                yR2 = (N - 1 - result2) / 2;
            }
            else {
                yP = (N - indPetya) / 2;
                yR1 = (N - result1) / 2;
                yR2 = (N - result2) / 2;
            }
            //Два вектора
            int vec1X = Math.abs(xP - xR1);
            int vec1Y = yR1 - yP;
            int vec2X = Math.abs(xP - xR2);
            int vec2Y = yP - yR2;

            //квадраты длин векторов
            vec1 = vec1X*vec1X + vec1Y*vec1Y;
            vec2 = vec2X*vec2X + vec2Y*vec2Y;

            if (vec1 < vec2)
            {
                //работаем с result1
                int variant;
                int ryad;
                if (result1 % 2 == 0)
                {
                    variant = 1;
                    ryad = (result1 + 2) / 2;
                }
                else
                {
                    variant = 2;
                    ryad = (result1 + 1) / 2;
                }
                System.out.println(ryad + " " + variant);
            }
            else
            {
                //работаем с result2
                int variant;
                int ryad;
                if (result2 % 2 == 0)
                {
                    variant = 1;
                    ryad = (result2 + 2) / 2;
                }
                else
                {
                    variant = 2;
                    ryad = (result2 + 1) / 2;
                }
                System.out.println(ryad + " " + variant);
            }


        }
    }
}
