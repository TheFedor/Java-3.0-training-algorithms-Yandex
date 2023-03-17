package com.company;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 26.Ход конем - 2 -lvl5 (Дивизион A)

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.close();

        BigInteger[][] desk = new BigInteger[N+3][M+3];

        for (int i = 0; i < N+3; ++i)
            for (int j = 0; j < M+3; ++j)
                desk[i][j] = new BigInteger("0");

        desk[2][2] = new BigInteger("1");
        int i = 2;
        int j = 2;

        while ((i < N+1) || (j < M+1))
        {
            if (j == M+1)
                i++;
            else
                j++;

            int thisI = i;
            int thisJ = j;
            while ((thisI <= N+1) && thisJ >= 2)
            {
                desk[thisI][thisJ] = new BigInteger((desk[thisI+1][thisJ-2].add(desk[thisI-1][thisJ-2]).add(desk[thisI-2][thisJ-1]).add(desk[thisI-2][thisJ+1])).toString());
                thisI++;
                thisJ--;
            }
        }

        System.out.println(desk[N+1][M+1]);

    }
}
