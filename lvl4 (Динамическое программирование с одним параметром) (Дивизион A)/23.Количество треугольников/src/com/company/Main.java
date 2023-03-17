package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 23.Количество треугольников - lvl4 (Дивизион А)

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.close();

        long answer = (long)Math.pow((long)N, 2);

        int count = 0;
        long addingTo = 0;

        for (int i = 1; i < N; ++i)
        {
            answer += i*(long)(N-i);
            answer += addingTo * (N-i);
            count++;
            if (count == 2)
            {
                addingTo++;
                count = 0;
            }

        }

        System.out.println(answer);


    }
}
