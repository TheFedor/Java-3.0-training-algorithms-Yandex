package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// 21.Три единицы подряд - lvl4

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.close();

        int[] func = new int[N+1];
        func[1] = 2;
        if (N > 1)
            func[2] = 4;
        if (N > 2)
            func[3] = 7;

        for (int i = 4; i <= N; ++i)
            func[i] = func[i-1] + func[i-2] + func[i-3];

        System.out.println(func[N]);

    }
}
